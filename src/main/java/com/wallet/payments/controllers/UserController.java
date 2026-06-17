package com.wallet.payments.controllers;

import com.wallet.payments.dtos.requests.UserRequestDTO;
import com.wallet.payments.dtos.responses.UserResponseDTO;
import com.wallet.payments.models.User;
import com.wallet.payments.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("create")
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO userDto) {
        UserResponseDTO savedUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(savedUserDto, HttpStatus.CREATED);
    }
}
