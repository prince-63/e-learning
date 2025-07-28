package com.e_learning.auth_service.mappers;

import com.e_learning.auth_service.dto.AuthResponseDTO;
import com.e_learning.auth_service.dto.LoginResponseDTO;
import com.e_learning.auth_service.dto.RegisterRequestDTO;
import com.e_learning.auth_service.entities.Role;
import com.e_learning.auth_service.entities.User;

import java.util.Set;

public class UserMapper {

    public static AuthResponseDTO toUserResponseDTO(User user) {
        return AuthResponseDTO.builder()
                .userId(user.getUserId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .active(user.isActive())
                .build();
    }

    public static LoginResponseDTO toLoginDTO(String jwtToken) {
        return new LoginResponseDTO(jwtToken);
    }

    public static User toUserModel(RegisterRequestDTO registerRequestDTO) {
        return User.builder()
                .fullName(registerRequestDTO.getFullName())
                .email(registerRequestDTO.getEmail())
                .role(Set.of(Role.valueOf(registerRequestDTO.getRole())))
                .build();
    }

}
