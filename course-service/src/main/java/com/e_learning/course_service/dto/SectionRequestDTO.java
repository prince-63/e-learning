package com.e_learning.course_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionRequestDTO {

    @NotBlank(message = "Title of the section should not be null or empty")
    @Schema(description = "Title of the section", example = "Introduction to Spring Boot")
    private String title;

    @PositiveOrZero(message = "order should must be positive number")
    @Schema(description = "Order of the section in the course", example = "1")
    private int order;

}
