package com.e_learning.user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@MappedSuperclass
public class ProfessionRequestDTO {

    @Schema(description = "User's profession or job title", example = "Software Engineer")
    @NotBlank(message = "Profession must not be blank")
    @Size(min = 2, max = 50, message = "Profession must be between 2 and 50 characters")
    private String profession;
}
