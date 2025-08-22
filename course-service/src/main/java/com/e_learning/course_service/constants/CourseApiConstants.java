package com.e_learning.course_service.constants;

import static com.e_learning.course_service.constants.BaseApiPathConstants.BASE;

public final class CourseApiConstants {
    private static final String COURSE_BASE = BASE + "course/";
    public static final String CREATE_COURSE = COURSE_BASE + "add";
    public static final String ADD_THUMBNAIL = COURSE_BASE + "add-thumbnail";
    public static final String UPDATE_COURSE_DETAILS = COURSE_BASE + "update";
    public static final String GET_COURSE_DETAILS = COURSE_BASE + "get/{courseId}";
    public static final String DELETE_COURSE_DETAILS = COURSE_BASE + "delete/{courseId}";
    public static final String GET_ALL_COURSE_DETAILS = COURSE_BASE + "getAll";
}
