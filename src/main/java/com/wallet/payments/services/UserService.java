package com.wallet.payments.services;

import com.wallet.payments.dtos.requests.UserRequestDTO;
import com.wallet.payments.dtos.responses.UserResponseDTO;
import com.wallet.payments.mappers.UserMapper;
import com.wallet.payments.models.User;
import com.wallet.payments.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponseDTO> responseDtos = new ArrayList<>();
        for(User user: users) {
            UserResponseDTO userDto = userMapper.toResponseDtoFromEntity(user);
            responseDtos.add(userDto);
        }
        return responseDtos;
    }

    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRequestDTO != null) {
            User toBeSavedUser = userMapper.toEntityFromUserRequestDto(userRequestDTO);
            User savedUser = userRepository.save(toBeSavedUser);
            UserResponseDTO responseDTO = userMapper.toResponseDtoFromEntity(savedUser);
            return responseDTO;
        }
        return null;
    }
}
