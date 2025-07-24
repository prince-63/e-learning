package com.e_learning.presigned_url_generator_service.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

@UtilityClass
public class PDFFileUtils {

    private static final long MAX_FILE_SIZE = 1000L * 1024 * 1024; // 1 GB
    private static final String PDF_PATTERN = "([^\\s]+(\\.(?i)(pdf))$)";
    private static final String TYPE_DESC = "pdf";

    public void assertAllowed(MultipartFile file) {
        FileUtils.assertAllowed(file, PDF_PATTERN, MAX_FILE_SIZE, TYPE_DESC);
    }

    public String getFileName(String name) {
        return FileUtils.generateFileName(name);
    }
}

