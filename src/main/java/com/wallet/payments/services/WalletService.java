package com.wallet.payments.services;

import com.wallet.payments.dtos.requests.WalletRequestDTO;
import com.wallet.payments.dtos.responses.WalletResponseDTO;
import com.wallet.payments.enums.WalletStatus;
import com.wallet.payments.mappers.WalletMapper;
import com.wallet.payments.models.User;
import com.wallet.payments.models.Wallet;
import com.wallet.payments.repositories.WalletRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WalletService {

    @Getter
    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private WalletMapper walletMapper;

    @Autowired
    public void setWalletRepository(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet saveWallet(WalletRequestDTO requestDTO) {
        Wallet unsavedWallet = walletMapper.toEntityFromWalletRequestDto(requestDTO);
        return walletRepository.save(unsavedWallet);
    }

    public WalletResponseDTO createWallet(WalletRequestDTO walletRequestDTO, User user) {
        Wallet wallet = new Wallet();
        wallet.setBalance(walletRequestDTO.getBalance());
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());
        wallet.setStatus(WalletStatus.ACTIVE);
        wallet.setUser(user);
        Wallet savedWallet = this.walletRepository.save(wallet);
        return walletMapper.toWalletResponseDtoFromEntity(savedWallet);
    }
}
