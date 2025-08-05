package com.e_learning.presigned_url_generator_service.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class FolderConstants {

    private static final String ROOT = "e-learning";

    public String profileFolderPath(Long userId) {
        return String.format("%s/user_id_%d/profile_picture/", ROOT, userId);
    }

    public String courseFolderPath(Long userId, String courseId) {
        return String.format("%s/user_id_%d/course_id_%s/", ROOT, userId, courseId);
    }

    public String sectionFolderPath(Long userId, String courseId, String sectionId) {
        return String.format("%s/section_id_%s/", courseFolderPath(userId, courseId), sectionId);
    }

    public String lectureFolderPath(Long userId, String courseId, String sectionId, String lectureId) {
        return String.format("%s/lecture_id_%s/", sectionFolderPath(userId, courseId, sectionId), lectureId);
    }
}