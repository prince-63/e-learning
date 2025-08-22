package com.e_learning.course_service.dto;

import com.e_learning.course_service.collections.Resource;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class LectureDetailsResponseDTO {

    @Schema(description = "Unique ID of the lecture", example = "64f2b512a1b0e80c742fc321")
    private String lectureId;

    @Schema(description = "Title of the lecture", example = "Getting Started with Spring Boot")
    private String title;

    @Schema(description = "Order of the lecture in the section", example = "1")
    private int order;

    @Schema(description = "Public id for video")
    private String videoUrlPublicId;

    @Schema(description = "Video url from the presigned url generator service", example = "https://cloudinary.com/kweindnjkd/video-uuid-12345.mp4")
    private String videoUrl;

    @Positive
    @Schema(description = "Duration of the lecture in seconds", example = "600")
    private int duration;

    @Schema(description = "Resources related to this lecture")
    private List<Resource> resourceUrls;

    @Schema(description = "True if this is a free preview lecture", example = "true")
    private boolean preview;

    @Schema(description = "Timestamp of lecture creation")
    private LocalDateTime createdAt;

    @Schema(description = "Timestamp of last lecture update")
    private LocalDateTime updatedAt;
}
