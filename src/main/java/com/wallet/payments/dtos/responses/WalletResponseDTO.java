package com.wallet.payments.dtos.responses;

import com.wallet.payments.enums.WalletStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletResponseDTO {
    private Long id;
    private BigDecimal balance = BigDecimal.ZERO;
    private WalletStatus status;
    private Long userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
