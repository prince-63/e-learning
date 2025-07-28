package com.e_learning.user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@MappedSuperclass
public class PhoneNumberRequestDTO {

    @Schema(description = "User's phone number in international format", example = "+919876543210")
    @Pattern(regexp = "^\\+?[0-9]{10,15}$", message = "Invalid phone number format. Must be 10 to 15 digits, optionally starting with '+'")
    private String phoneNumber;
}
