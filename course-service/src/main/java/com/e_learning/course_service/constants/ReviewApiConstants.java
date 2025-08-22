package com.e_learning.course_service.constants;

import static com.e_learning.course_service.constants.BaseApiPathConstants.BASE;

public class ReviewApiConstants {
    private static final String REVIEW_BASE = BASE + "reviews/";
    public static final String ADD_REVIEW = REVIEW_BASE + "add";
    public static final String UPDATE_REVIEW_DETAILS = REVIEW_BASE + "update";
    public static final String GET_ALL_REVIEWS = REVIEW_BASE + "get/{courseId}";
    public static final String DELETE_REVIEW = REVIEW_BASE + "delete/{courseId}";
}
