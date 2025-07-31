package com.e_learning.course_service.collections;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "courses")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Main Course document containing metadata for each course")
public class Course {

    @Id
    @Schema(description = "Unique ID for the course", example = "64f2b1a2ce60f31a3c7ec93f")
    private String id;

    @NotBlank
    @Schema(description = "Course title", example = "Mastering Spring Boot")
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

    @Schema(description = "Thumbnail image URL", example = "https://cdn.example.com/course-thumbnail.jpg")
    private String thumbnailUrl;

    @PositiveOrZero
    @Schema(description = "Original price of the course", example = "499.99")
    private double price;

    @PositiveOrZero
    @Schema(description = "Discounted price of the course", example = "299.99")
    private double discountedPrice;

    @NotBlank
    @Schema(description = "Instructor ID who created the course", example = "auth-user-123")
    private String instructorId;

    @Schema(description = "Publication status of the course", example = "true")
    private boolean published;

    @Schema(description = "Timestamp of course creation")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp of last course update")
    private LocalDateTime updatedAt;
}
