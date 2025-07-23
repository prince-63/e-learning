package com.e_learning.auth_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Generic response wrapper for all API responses")
public class ResponseDTO<T> {

    @Schema(description = "Response message giving brief details about the result", example = "Operation completed successfully")
    private String message;

    @Schema(description = "Indicates if the operation was successful", example = "true")
    private Boolean success;

    @Schema(description = "Response data payload of generic type")
    private T data;
}
