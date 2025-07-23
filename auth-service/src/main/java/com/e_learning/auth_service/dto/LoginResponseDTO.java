package com.e_learning.auth_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor @NoArgsConstructor
@Builder
@Schema(description = "Response containing JWT token after successful login")
public class LoginResponseDTO {

    @Schema(description = "JWT access token", example = "eyJhbGciOiJIUzI1NiIsInR...")
    private String jwtToken;

}
