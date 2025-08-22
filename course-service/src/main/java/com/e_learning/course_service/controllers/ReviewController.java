package com.e_learning.course_service.controllers;

import com.e_learning.course_service.collections.Review;
import com.e_learning.course_service.dto.ResponseDTO;
import com.e_learning.course_service.dto.ReviewRequestDTO;
import com.e_learning.course_service.dto.ReviewResponseDTO;
import com.e_learning.course_service.mappers.ReviewMapper;
import com.e_learning.course_service.services.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static com.e_learning.course_service.constants.ReviewApiConstants.*;

@RestController
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(ADD_REVIEW)
    public ResponseEntity<ResponseDTO<ReviewResponseDTO>> addReview(
            @RequestParam(name = "userId") String userId,
            @RequestParam(name = "courseId") String courseId,
            @RequestBody ReviewRequestDTO reviewRequestDTO
    ) {
        Review review = reviewService.addReview(userId, courseId, reviewRequestDTO);
        ResponseDTO<ReviewResponseDTO> response = new ResponseDTO<>();
        response.setMessage("Review Added Successfully");
        response.setSuccess(true);
        response.setData(ReviewMapper.toDTO(review));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PatchMapping(UPDATE_REVIEW_DETAILS)
    public ResponseEntity<ResponseDTO<ReviewResponseDTO>> updateReview(
            @RequestParam(name = "reviewId") String reviewId,
            @RequestBody ReviewRequestDTO reviewRequestDTO
    ) {
        Review review = reviewService.updateReview(reviewId, reviewRequestDTO);
        ResponseDTO<ReviewResponseDTO> response = new ResponseDTO<>();
        response.setMessage("Review Updated Successfully");
        response.setSuccess(true);
        response.setData(ReviewMapper.toDTO(review));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(GET_ALL_REVIEWS)
    public ResponseEntity<ResponseDTO<List<ReviewResponseDTO>>> getReviews(
            @PathVariable String courseId
    ) {
        List<ReviewResponseDTO> reviewList = reviewService.getReview(courseId).stream()
                .map(ReviewMapper::toDTO)
                .collect(Collectors.toList());

        ResponseDTO<List<ReviewResponseDTO>> response = new ResponseDTO<>();
        response.setMessage("Reviews Fetched Successfully");
        response.setSuccess(true);
        response.setData(reviewList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(DELETE_REVIEW)
    public ResponseEntity<ResponseDTO<Void>> deleteReview(
            @RequestParam(name = "reviewId", required = false) String reviewId,
            @PathVariable(required = false) String courseId
    ) {
        ResponseDTO<Void> response = new ResponseDTO<>();
        if (reviewId != null) {
            reviewService.deleteReview(reviewId);
            response.setMessage("Review Deleted Successfully");
        } else {
            reviewService.deleteReviewsByCourseId(courseId);
            response.setMessage("All Reviews for Course Deleted Successfully");
        }

        response.setSuccess(true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
