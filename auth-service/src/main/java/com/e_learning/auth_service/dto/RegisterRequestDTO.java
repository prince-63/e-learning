package com.e_learning.auth_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Request body for registering a new user")
public class RegisterRequestDTO {

    @NotBlank
    @Size(min = 2, max = 50)
    @Schema(description = "Full name of the user", example = "Alice Johnson", required = true)
    private String fullName;

    @NotBlank
    @Email
    @Schema(description = "User's email address", example = "alice@example.com", required = true)
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)
    @Schema(description = "Password for the account (minimum 6 characters)", example = "P@ssw0rd", required = true)
    private String pwd;

    @NotBlank
    @Schema(description = "Role of the user", example = "STUDENT", required = true)
    private String role;

}
