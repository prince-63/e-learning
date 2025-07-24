package com.e_learning.auth_service.mappers;

import com.e_learning.auth_service.dto.LoginResponseDTO;
import com.e_learning.auth_service.dto.UserRegisterRequestDTO;
import com.e_learning.auth_service.dto.UserResponseDTO;
import com.e_learning.auth_service.entities.Role;
import com.e_learning.auth_service.entities.User;

import java.util.Set;

public class UserMapper {

    public static UserResponseDTO toUserResponseDTO(User user) {
        return UserResponseDTO.builder()
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

    public static User toUserModel(UserRegisterRequestDTO userRegisterRequestDTO) {
        return User.builder()
                .fullName(userRegisterRequestDTO.getFullName())
                .email(userRegisterRequestDTO.getEmail())
                .role(Set.of(Role.valueOf(userRegisterRequestDTO.getRole())))
                .build();
    }

}
