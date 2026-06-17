package com.wallet.payments.exceptions;

public class WalletDeactivatedException extends RuntimeException {

    public WalletDeactivatedException(String message) {
        super(message);
    }
}
