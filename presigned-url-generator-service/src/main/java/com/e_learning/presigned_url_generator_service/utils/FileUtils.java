package com.e_learning.presigned_url_generator_service.utils;

import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class FileUtils {

    private static final String DEFAULT_DATE_FORMAT = "yyyyMMddHHmmss";
    private static final String DEFAULT_FILE_NAME_FORMAT = "%s_%s";

    public boolean isAllowedExtension(String fileName, String pattern) {
        if (fileName == null || pattern == null) return false;
        Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(fileName);
        return matcher.matches();
    }

    public void assertAllowed(MultipartFile file, String pattern, long maxFileSize, String typeDescription) {
        if (file.getSize() > maxFileSize) {
            throw new RuntimeException("Max file size allowed: " + (maxFileSize / (1024 * 1024)) + "MB");
        }

        final String fileName = file.getOriginalFilename();
        if (!isAllowedExtension(fileName, pattern)) {
            throw new RuntimeException("Only " + typeDescription + " files are allowed");
        }
    }

    public String generateFileName(String baseName) {
        DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
        String timestamp = dateFormat.format(System.currentTimeMillis());
        return String.format(DEFAULT_FILE_NAME_FORMAT, baseName, timestamp);
    }

}
