package com.e_learning.course_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SectionResponseDTO {

    @Schema(description = "Unique ID of the section", example = "64f2b3a1a3c0f22b7c8bc841")
    private String sectionId;

    @Schema(description = "ID of the course this section belongs to", example = "64f2b1a2ce60f31a3c7ec93f")
    private String courseId;

    @Schema(description = "Title of the section", example = "Introduction to Spring Boot")
    private String title;

    @Schema(description = "Order of the section in the course", example = "1")
    private int order;

    @Schema(description = "Timestamp of last section update")
    private LocalDateTime updatedAt;

}
