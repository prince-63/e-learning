package com.e_learning.course_service.constants;

import static com.e_learning.course_service.constants.BaseApiPathConstants.BASE;

public final class LectureApiConstants {
    private static final String LECTURE_BASE = BASE + "lecture/";
    public static final String ADD_LECTURE = LECTURE_BASE + "add";
    public static final String UPDATE_LECTURE = LECTURE_BASE + "update";
    public static final String UPLOAD_LECTURE_VIDEO = LECTURE_BASE + "video/upload";
    public static final String UPLOAD_LECTURE_RESOURCE = LECTURE_BASE + "resource/upload";
}
