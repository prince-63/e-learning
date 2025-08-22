package com.e_learning.course_service.collections;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@Builder
public class Review {

    @Id
    private String reviewId;

    private String userId;

    @NotNull
    private String courseId;

    private String message;

    @NotNull
    private double rating;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
