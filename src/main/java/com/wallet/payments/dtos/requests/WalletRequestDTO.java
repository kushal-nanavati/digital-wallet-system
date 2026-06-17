package com.wallet.payments.dtos.requests;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletRequestDTO {
    @NotNull(message = "User ID is required.")
    private Long userId;

    private BigDecimal balance = BigDecimal.ZERO;
}
