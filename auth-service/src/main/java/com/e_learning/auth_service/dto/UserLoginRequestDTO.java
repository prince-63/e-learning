package com.e_learning.auth_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Request body for user login")
public class UserLoginRequestDTO {

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email should be valid")
    @Schema(description = "User email address", example = "user@example.com", required = true)
    private String email;

    @NotBlank(message = "Password must not be blank")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    @Schema(description = "User password", example = "securePass123", required = true)
    private String pwd;

}
