package com.wallet.payments.mappers;

import com.wallet.payments.dtos.requests.WalletRequestDTO;
import com.wallet.payments.dtos.responses.WalletResponseDTO;
import com.wallet.payments.models.Wallet;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class WalletMapper {

    public Wallet toEntityFromWalletRequestDto(WalletRequestDTO walletDto) {
        if(walletDto == null) {
            return null;
        }
        Wallet wallet = new Wallet();
        wallet.setBalance(walletDto.getBalance());
        wallet.setCreatedAt(LocalDateTime.now());
        wallet.setUpdatedAt(LocalDateTime.now());
        return wallet;
    }

    public WalletResponseDTO toWalletResponseDtoFromEntity(Wallet wallet) {
        if(wallet == null) {
            return null;
        }
        WalletResponseDTO responseDTO = new WalletResponseDTO();
        responseDTO.setId(wallet.getId());
        responseDTO.setBalance(wallet.getBalance());
        responseDTO.setStatus(wallet.getStatus());
        responseDTO.setUserId(wallet.getUser().getId());
        responseDTO.setCreatedAt(wallet.getCreatedAt());
        responseDTO.setUpdatedAt(wallet.getUpdatedAt());
        return responseDTO;
    }
}
