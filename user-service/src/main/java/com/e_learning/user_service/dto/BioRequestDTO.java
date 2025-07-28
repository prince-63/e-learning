package com.e_learning.user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@MappedSuperclass
public class BioRequestDTO {

    @Schema(description = "User bio or description", example = "Software engineer passionate about e-learning and Java.")
    @Size(max = 500, message = "Bio must not exceed 500 characters")
    private String bio;

}
