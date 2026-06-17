package com.wallet.payments.services;

import com.wallet.payments.dtos.responses.WalletResponseDTO;
import com.wallet.payments.enums.WalletStatus;
import com.wallet.payments.exceptions.InsufficientFundsException;
import com.wallet.payments.exceptions.ResourceNotFoundException;
import com.wallet.payments.exceptions.WalletDeactivatedException;
import com.wallet.payments.mappers.WalletMapper;
import com.wallet.payments.models.Account;
import com.wallet.payments.models.User;
import com.wallet.payments.models.Wallet;
import com.wallet.payments.repositories.AccountRepository;
import com.wallet.payments.repositories.UserRepository;
import com.wallet.payments.repositories.WalletRepository;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class WalletService {

    @Getter
    @Autowired
    private WalletRepository walletRepository;

    @Getter
    @Autowired
    private UserRepository userRepository;

    @Getter
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    public void setWalletRepository(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public WalletResponseDTO saveWallet(Long userId) throws ResourceNotFoundException {
        User isExistingUser = this.userRepository.findById(userId).orElseThrow(() -> { return new ResourceNotFoundException("Something went wrong. Please try again later."); });
        Wallet newWallet = new Wallet();
        newWallet.setBalance(BigDecimal.ZERO);
        newWallet.setStatus(WalletStatus.CREATED);
        newWallet.setCreatedAt(LocalDateTime.now());
        newWallet.setUpdatedAt(LocalDateTime.now());
        newWallet.setUser(isExistingUser);
        Wallet wallet = this.walletRepository.save(newWallet);
        return this.walletMapper.toWalletResponseDtoFromEntity(wallet);
    }

    public WalletResponseDTO addMoneyToWallet(Long userId, BigDecimal amount) throws ResourceNotFoundException, InsufficientFundsException {
        Account account = this.accountRepository.findByUserId(userId);
        if(account == null) {
            throw new ResourceNotFoundException("Account not found for the given user ID.");
        }
        // Account for the given user exists
        BigDecimal existingBalance = account.getBalance();
        if(existingBalance.compareTo(amount) < 0) {
            throw new InsufficientFundsException("Insufficient balance in the account.");
        }
        BigDecimal newBalance = existingBalance.subtract(amount);
        account.setBalance(newBalance);
        Account updatedAccount = this.accountRepository.save(account);

        // Credit the amount to the wallet
        if(account.getWallet() != null) {
            if(account.getWallet().getStatus() != WalletStatus.BLOCKED) {
                Wallet wallet = account.getWallet();
                wallet.setBalance(wallet.getBalance().add(amount));
                wallet.setStatus(WalletStatus.ACTIVE);
                return this.walletMapper.toWalletResponseDtoFromEntity(this.walletRepository.save(wallet));
            }
            throw new WalletDeactivatedException("Wallet is deactivated. Please activate it first.");
        }

        // If wallet isn't present, then simply create it with the given amount
        User isExistingUser = this.userRepository.findById(userId).orElseThrow(() -> { return new ResourceNotFoundException("User with this id doesn't exist"); });
        Wallet wallet = new Wallet();
        wallet.setBalance(amount);
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());
        wallet.setStatus(WalletStatus.ACTIVE);
        wallet.setUser(isExistingUser);
        return this.walletMapper.toWalletResponseDtoFromEntity(this.walletRepository.save(wallet));
    }

    public WalletResponseDTO deactivateWallet(Long userId) {
        Wallet w = this.walletRepository.findByUserId(userId);
        if(w != null) {
            w.setStatus(WalletStatus.BLOCKED);
            return this.walletMapper.toWalletResponseDtoFromEntity(this.walletRepository.save(w));
        }
        throw new ResourceNotFoundException("Wallet doesn't exist for this user.");
    }
}
