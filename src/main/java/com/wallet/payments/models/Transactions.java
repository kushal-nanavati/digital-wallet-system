package com.wallet.payments.models;

import com.wallet.payments.enums.TransactionStatus;
import com.wallet.payments.enums.TransactionType;
import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal amount = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name = "walletId")
    private Wallet wallet;

    public Transactions() {}

    public Transactions(Long id, BigDecimal amount, TransactionStatus transactionStatus, TransactionType type, LocalDateTime updatedAt, Wallet wallet) {
        this.id = id;
        this.amount = amount;
        this.transactionStatus = transactionStatus;
        this.type = type;
        this.updatedAt = updatedAt;
        this.wallet = wallet;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public TransactionType getTransactionType() {
        return type;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.type = transactionType;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public void setWallet(Wallet wallet) {
        this.wallet = wallet;
    }
}
