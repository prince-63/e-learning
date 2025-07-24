package com.e_learning.presigned_url_generator_service.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

@UtilityClass
public class ImageFileUtils {

    private static final long MAX_FILE_SIZE = 10L * 1024 * 1024; // 2 MB
    private static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|jpeg|png|gif|bmp))$)";
    private static final String TYPE_DESC = "jpg, jpeg, png, gif, bmp";

    public void assertAllowed(MultipartFile file) {
        FileUtils.assertAllowed(file, IMAGE_PATTERN, MAX_FILE_SIZE, TYPE_DESC);
    }

    public String getFileName(String name) {
        return FileUtils.generateFileName(name);
    }
}
