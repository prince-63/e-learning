package com.e_learning.course_service.mappers;

import com.e_learning.course_service.collections.Review;
import com.e_learning.course_service.dto.ReviewRequestDTO;
import com.e_learning.course_service.dto.ReviewResponseDTO;

public class ReviewMapper {

    public static Review toCollection(String userId, String courseId, ReviewRequestDTO review) {
        return Review.builder()
                .courseId(courseId)
                .userId(userId)
                .rating(review.getRating())
                .message(review.getMessage())
                .build();
    }

    public static ReviewResponseDTO toDTO(Review review) {
        return ReviewResponseDTO.builder()
                .reviewId(review.getReviewId())
                .userId(review.getUserId())
                .rating(review.getRating())
                .message(review.getMessage())
                .courseId(review.getCourseId())
                .createdAt(review.getCreatedAt())
                .updatedAt(review.getUpdatedAt())
                .build();
    }

}
