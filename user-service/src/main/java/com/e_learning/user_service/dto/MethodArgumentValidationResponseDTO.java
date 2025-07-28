package com.e_learning.user_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Schema(description = "Response DTO representing detailed validation errors for invalid method arguments")
public class MethodArgumentValidationResponseDTO extends ErrorResponseDTO {

    @Schema(
            description = "A map containing field names as keys and corresponding validation error messages as values",
            example = "{\"email\": \"Email must be valid\", \"password\": \"Password must be at least 8 characters\"}"
    )
    private Map<String, String> validationErrors;

    public MethodArgumentValidationResponseDTO(String apiPath, int errorCode, String errorMessage, LocalDateTime errorTime, Map<String, String> validationErrors) {
        super(apiPath, errorCode, errorMessage, errorTime);
        this.validationErrors = validationErrors;
    }

}
