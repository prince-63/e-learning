package com.e_learning.course_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewRequestDTO {

    @Schema(description = "Message of review")
    private String message;

    @NotNull(message = "Rating should not be null")
    @Schema(description = "Rating of course")
    private double rating;

}
