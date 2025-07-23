package com.e_learning.auth_service.mappers;

import com.e_learning.auth_service.dto.LoginResponseDTO;

public class LoginResponseMapper {

    public LoginResponseDTO toDTO(String jwtToken) {
        return new LoginResponseDTO(jwtToken);
    }
}
