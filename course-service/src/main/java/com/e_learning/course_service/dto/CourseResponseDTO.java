package com.e_learning.course_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponseDTO {

    @Schema(description = "Unique ID for the course", example = "64f2b1a2ce60f31a3c7ec93f")
    private String id;

    @Schema(description = "Course title", example = "Mastering Spring Boot")
    private String title;

    @Schema(description = "URL-friendly slug for the course", example = "mastering-spring-boot")
    private String slug;

    @Schema(description = "Full course description", example = "This course covers Spring Boot from beginner to advanced.")
    private String description;

    @Schema(description = "Course category", example = "Development")
    private String category;

    @Schema(description = "Course subcategory", example = "Backend Development")
    private String subcategory;

    @Schema(description = "Tags for better course searchability", example = "[\"Spring Boot\", \"Java\", \"Backend\"]")
    private List<@NotBlank String> tags;

    @Schema(description = "Course language", example = "English")
    private String language;

    @Schema(description = "Difficulty level", example = "Beginner")
    private String level;

    @Schema(description = "Thumbnail image URL", example = "https://cdn.example.com/course-thumbnail.jpg")
    private String thumbnailUrl;

    @Schema(description = "Original price of the course", example = "499.99")
    private double price;

    @Schema(description = "Discounted price of the course", example = "299.99")
    private double discountedPrice;

    @Schema(description = "Instructor ID who created the course", example = "123")
    private Long instructorId;

    @Schema(description = "Publication status of the course", example = "true")
    private boolean published;

}
