package com.e_learning.presigned_url_generator_service.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

@UtilityClass
public class VideoFileUtils {

    private static final long MAX_FILE_SIZE = 4000L * 1024 * 1024; // 4 GB
    private static final String VIDEO_PATTERN = "([^\\s]+(\\.(?i)(mp4|mkv|avi|mov))$)";
    private static final String TYPE_DESC = "mp4, mkv, avi, mov";

    public void assertAllowed(MultipartFile file) {
        FileUtils.assertAllowed(file, VIDEO_PATTERN, MAX_FILE_SIZE, TYPE_DESC);
    }

    public String getFileName(String name) {
        return FileUtils.generateFileName(name);
    }
}
