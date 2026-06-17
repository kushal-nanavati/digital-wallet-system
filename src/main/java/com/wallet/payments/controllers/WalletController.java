package com.wallet.payments.controllers;

import com.wallet.payments.dtos.requests.WalletRequestDTO;
import com.wallet.payments.dtos.responses.WalletResponseDTO;
import com.wallet.payments.exceptions.InsufficientFundsException;
import com.wallet.payments.exceptions.ResourceNotFoundException;
import com.wallet.payments.services.WalletService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Getter
@RestController
@RequestMapping("/api/wallet")
public class WalletController {

    private WalletService walletService;

    @Autowired
    public void setWalletService(WalletService walletService) {
        this.walletService = walletService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createWallet(@RequestBody WalletRequestDTO requestDTO) {
        try {
            WalletResponseDTO responseDTO = this.walletService.saveWallet(requestDTO.getUserId());
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add-money")
    public ResponseEntity<?> addMoneyToWallet(@RequestBody WalletRequestDTO requestDTO) {
        try {
            WalletResponseDTO responseDTO = this.walletService.addMoneyToWallet(requestDTO.getUserId(), requestDTO.getBalance());
            return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
        }
        catch(ResourceNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch(InsufficientFundsException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
