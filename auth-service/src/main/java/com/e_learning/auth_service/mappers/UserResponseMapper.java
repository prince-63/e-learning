package com.e_learning.auth_service.mappers;

import com.e_learning.auth_service.dto.UserResponseDTO;
import com.e_learning.auth_service.entities.User;

public class UserResponseMapper {

    public static UserResponseDTO toDTO(User user) {
        return UserResponseDTO.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.isActive())
                .build();
    }

}
