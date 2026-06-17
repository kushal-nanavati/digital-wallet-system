package com.wallet.payments.repositories;

import com.wallet.payments.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {
    public Wallet findByUserId(Long userId);
}
