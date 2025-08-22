package com.e_learning.course_service.services;

import com.e_learning.course_service.collections.Review;
import com.e_learning.course_service.dto.ReviewRequestDTO;

import java.util.List;

public interface ReviewService {

    Review addReview(String userId, String courseId, ReviewRequestDTO review);

    Review updateReview(String reviewId, ReviewRequestDTO review);

    List<Review> getReview(String courseId);

    void deleteReview(String reviewId);

    void deleteReviewsByCourseId(String courseId);

}
