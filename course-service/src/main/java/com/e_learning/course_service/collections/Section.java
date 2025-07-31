package com.e_learning.course_service.collections;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Document(collection = "sections")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Section inside a course containing multiple lectures")
public class Section {

    @Id
    @Schema(description = "Unique ID of the section", example = "64f2b3a1a3c0f22b7c8bc841")
    private String id;

    @NotBlank
    @Schema(description = "ID of the course this section belongs to", example = "64f2b1a2ce60f31a3c7ec93f")
    private String courseId;

    @NotBlank
    @Schema(description = "Title of the section", example = "Introduction to Spring Boot")
    private String title;

    @PositiveOrZero
    @Schema(description = "Order of the section in the course", example = "1")
    private int order;

    @Schema(description = "Timestamp of section creation")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp of last section update")
    private LocalDateTime updatedAt;
}

