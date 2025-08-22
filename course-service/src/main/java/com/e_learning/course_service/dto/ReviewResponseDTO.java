package com.e_learning.course_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ReviewResponseDTO {
    private String reviewId;
    private String userId;
    private String courseId;
    private String message;
    private double rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
