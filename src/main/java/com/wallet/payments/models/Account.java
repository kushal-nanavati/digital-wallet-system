package com.wallet.payments.models;

import com.wallet.payments.enums.AccountType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

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
    private String accountHolder;

    @Column(nullable = true)
    private BigDecimal balance = BigDecimal.ZERO;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "userId", unique = true, nullable = true)
    private User user;

    @OneToOne
    @ToString.Exclude
    @JoinColumn(name = "walletId", unique = true, nullable = true)
    private Wallet wallet;
}
