package com.e_learning.course_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseRequestDTO {

    @NotBlank
    @Schema(description = "Course title", example = "Mastering Spring Boot")
    @Size(min = 1, max = 200, message = "The length of the title should be 1 to 200 characters")
    private String title;

    @NotBlank
    @Schema(description = "URL-friendly slug for the course", example = "mastering-spring-boot")
    private String slug;

    @NotBlank
    @Schema(description = "Full course description", example = "This course covers Spring Boot from beginner to advanced.")
    private String description;

    @NotBlank
    @Schema(description = "Course category", example = "Development")
    private String category;

    @NotBlank
    @Schema(description = "Course subcategory", example = "Backend Development")
    private String subcategory;

    @NotNull
    @Size(min = 1)
    @Schema(description = "Tags for better course searchability", example = "[\"Spring Boot\", \"Java\", \"Backend\"]")
    private List<@NotBlank String> tags;

    @NotBlank
    @Schema(description = "Course language", example = "English")
    private String language;

    @NotBlank
    @Schema(description = "Difficulty level", example = "Beginner")
    private String level;

    @PositiveOrZero
    @Schema(description = "Original price of the course", example = "499.99")
    private double price;

    @PositiveOrZero
    @Schema(description = "Discounted price of the course", example = "299.99")
    private double discountedPrice;

    @NotNull
    @Schema(description = "Instructor ID who created the course", example = "auth-user-123")
    private Long instructorId;

}
