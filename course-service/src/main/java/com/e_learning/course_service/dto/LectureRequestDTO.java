package com.e_learning.course_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LectureRequestDTO {

    @NotBlank(message = "Lecture title must be not empty")
    @Schema(description = "Title of the lecture", example = "Getting Started with Spring Boot")
    private String title;

    @PositiveOrZero(message = "Lecture Order should not be null or negative")
    @Schema(description = "Order of the lecture in the section", example = "1")
    private int order;

    @Positive(message = "Lecture duration must be not null")
    @Schema(description = "Duration of the lecture in seconds", example = "600")
    private int duration;

}
