package com.e_learning.course_service.services.impl;

import com.e_learning.course_service.collections.Review;
import com.e_learning.course_service.dto.ReviewRequestDTO;
import com.e_learning.course_service.exceptions.NotFoundException;
import com.e_learning.course_service.mappers.ReviewMapper;
import com.e_learning.course_service.repositories.ReviewRepository;
import com.e_learning.course_service.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    @Override
    public Review addReview(String userId, String courseId, ReviewRequestDTO review) {
        Review rev = ReviewMapper.toCollection(userId, courseId, review);
        rev.setCreatedAt(LocalDateTime.now());
        return reviewRepository.save(rev);
    }

    @Override
    public Review updateReview(String reviewId, ReviewRequestDTO review) {
        Review dbReview = loadReview(reviewId);
        dbReview.setRating(review.getRating() > 0 ? review.getRating() : dbReview.getRating());
        dbReview.setMessage(review.getMessage() != null ? review.getMessage() : dbReview.getMessage());
        dbReview.setUpdatedAt(LocalDateTime.now());
        return reviewRepository.save(dbReview);
    }

    @Override
    public List<Review> getReview(String courseId) {
        return reviewRepository.findAllByCourseId(courseId);
    }

    @Override
    public void deleteReview(String reviewId) {
        reviewRepository.deleteById(reviewId);
    }

    @Override
    public void deleteReviewsByCourseId(String courseId) {
        reviewRepository.deleteAllByCourseId(courseId);
    }

    private Review loadReview(String reviewId) {
        return reviewRepository.findById(reviewId).orElseThrow(() ->  new NotFoundException("Review not found with id: " + reviewId));
    }
}
