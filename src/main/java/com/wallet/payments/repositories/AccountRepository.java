package com.wallet.payments.repositories;

import com.wallet.payments.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    public Account findByUserId(Long userId);
}
