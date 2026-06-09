package com.wallet.payments.services;

import com.wallet.payments.models.Wallet;
import com.wallet.payments.repositories.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletService {

    private WalletRepository walletRepository;

    public WalletRepository getWalletRepository() {
        return walletRepository;
    }

    @Autowired
    public void setWalletRepository(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Wallet createWallet(Wallet wallet) {
        return this.walletRepository.save(wallet);
    }
}
