package com.e_learning.presigned_url_generator_service.constants;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class FolderConstants {

    private static final String ROOT = "e-learning";

    public String profileFolderPath(Long userId) {
        return String.format("%s/user_id_%d/profile_picture/", ROOT, userId);
    }

    public String courseFolderPath(Long userId, Long courseId) {
        return String.format("%s/user_id_%d/course_id_%d/", ROOT, userId, courseId);
    }

    public String sectionFolderPath(Long userId, Long courseId, Long sectionId) {
        return String.format("%s/section_id_%d/", courseFolderPath(userId, courseId), sectionId);
    }
}