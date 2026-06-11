package com.wallet.payments.mappers;

import com.wallet.payments.dtos.requests.UserRequestDTO;
import com.wallet.payments.dtos.responses.UserResponseDTO;
import com.wallet.payments.models.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User toEntityFromUserRequestDto(UserRequestDTO requestDTO) {
        if(requestDTO == null) {
            return null;
        }
        User user = new User();
        user.setUsername(requestDTO.getUsername());
        user.setEmail(requestDTO.getEmail());
        user.setPassword(requestDTO.getPassword());
        return user;
    }

    public UserResponseDTO toResponseDtoFromEntity(User user) {
        if(user == null) {
            return null;
        }
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setUsername(user.getUsername());
        responseDTO.setEmail(user.getEmail());
        return responseDTO;
    }

    public boolean updateUserEntity(UserRequestDTO requestDto, User user) {
        if(requestDto == null || user == null) {
            return false;
        }
        boolean needUpdates = false;
        if(!requestDto.getUsername().equals(user.getUsername())) {
            user.setUsername(requestDto.getUsername());
            needUpdates = true;
        }
        if(!requestDto.getEmail().equals(user.getEmail())) {
            user.setEmail(requestDto.getEmail());
            needUpdates = true;
        }
        if(!requestDto.getPassword().equals(user.getPassword())) {
            user.setPassword(requestDto.getPassword());
            needUpdates = true;
        }
        return needUpdates;
    }
}
