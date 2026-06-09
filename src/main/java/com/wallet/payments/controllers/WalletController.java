package com.wallet.payments.controllers;

import com.wallet.payments.enums.WalletStatus;
import com.wallet.payments.models.Wallet;
import com.wallet.payments.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletController {

    private WalletService walletService;

    public WalletService getWalletService() {
        return walletService;
    }

    @Autowired
    public void setWalletService(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/api/create-wallet")
    public String createWallet(@RequestBody Wallet wallet) {
        Wallet w = this.walletService.createWallet(wallet);
        if(w != null) {
            return "Wallet created successfully.";
        }
        else {
            return "Wallet creation failed.";
        }
    }
}
