package com.wallet.payments.models;

import com.wallet.payments.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "account")
public class Account {

    @Id
    private Long accountNumber;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(nullable = false)
    private AccountType accountType;

    @ManyToOne
    @JoinColumn(name = "walletId")
    private Wallet wallet;
}
