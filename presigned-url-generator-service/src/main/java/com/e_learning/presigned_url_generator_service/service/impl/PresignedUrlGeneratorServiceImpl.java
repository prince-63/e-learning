package com.e_learning.presigned_url_generator_service.service.impl;

import com.cloudinary.Cloudinary;
import com.e_learning.presigned_url_generator_service.constants.FolderConstants;
import com.e_learning.presigned_url_generator_service.service.PresignedUrlGeneratorService;
import com.e_learning.presigned_url_generator_service.utils.ImageFileUtils;
import com.e_learning.presigned_url_generator_service.utils.PDFFileUtils;
import com.e_learning.presigned_url_generator_service.utils.VideoFileUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

@Slf4j
@Service
@AllArgsConstructor
public class PresignedUrlGeneratorServiceImpl implements PresignedUrlGeneratorService {

    private final Cloudinary cloudinary;

    @Override
    public Map<String, String> generateSectionPdfUrl(MultipartFile file, Long userId, Long courseId, Long sectionId) {
        try {
            String fileName = PDFFileUtils.getFileName(FilenameUtils.getBaseName(file.getOriginalFilename()));
            File tempFile = convertMultiPartToFile(file);

            String publicId = FolderConstants.sectionFolderPath(userId, courseId, sectionId) + "documents/" + fileName;
            Map<String, Object> uploadOptions = Map.of("resource_type", "raw", "public_id", publicId);
            Map uploadResult = cloudinary.uploader().upload(tempFile, uploadOptions);

            deleteTempFile(tempFile);
            return Map.of(
                    "public_id", (String) uploadResult.get("public_id"),
                    "url", (String) uploadResult.get("secure_url")
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload section document: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, String> generateCourseVideoUrl(MultipartFile file, Long userId, Long courseId) {
        try {
            String fileName = VideoFileUtils.getFileName(FilenameUtils.getBaseName(file.getOriginalFilename()));
            File tempFile = convertMultiPartToFile(file);

            String publicId = FolderConstants.courseFolderPath(userId, courseId) + "videos/" + fileName;
            Map<String, Object> uploadOptions = Map.of("resource_type", "video", "public_id", publicId);
            Map uploadResult = cloudinary.uploader().upload(tempFile, uploadOptions);

            deleteTempFile(tempFile);
            return Map.of(
                    "public_id", (String) uploadResult.get("public_id"),
                    "url", (String) uploadResult.get("secure_url")
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload course video: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, String> generateCourseImageUrl(MultipartFile file, Long userId, Long courseId) {
        try {
            String fileName = ImageFileUtils.getFileName(FilenameUtils.getBaseName(file.getOriginalFilename()));
            File tempFile = convertMultiPartToFile(file);

            String publicId = FolderConstants.courseFolderPath(userId, courseId) + "images/" + fileName;
            Map<String, Object> uploadOptions = Map.of("public_id", publicId);
            Map uploadResult = cloudinary.uploader().upload(tempFile, uploadOptions);

            deleteTempFile(tempFile);
            return Map.of(
                    "public_id", (String) uploadResult.get("public_id"),
                    "url", (String) uploadResult.get("secure_url")
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload course image: " + e.getMessage(), e);
        }
    }

    @Override
    public Map<String, String> generateProfileImageUrl(MultipartFile file, Long userId) {
        try {
            String fileName = ImageFileUtils.getFileName(FilenameUtils.getBaseName(file.getOriginalFilename()));
            File tempFile = convertMultiPartToFile(file);

            String publicId = FolderConstants.profileFolderPath(userId) + fileName;
            Map<String, Object> uploadOptions = Map.of("public_id", publicId);
            Map uploadResult = cloudinary.uploader().upload(tempFile, uploadOptions);

            deleteTempFile(tempFile);
            return Map.of(
                    "public_id", (String) uploadResult.get("public_id"),
                    "url", (String) uploadResult.get("secure_url")
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload profile image: " + e.getMessage(), e);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        String prefix = "upload_";
        String suffix = "." + FilenameUtils.getExtension(file.getOriginalFilename());
        File tempFile = Files.createTempFile(prefix, suffix).toFile();

        try (FileOutputStream fos = new FileOutputStream(tempFile)) {
            fos.write(file.getBytes());
        }

        return tempFile;
    }

    private void deleteTempFile(File uploadedFile) {
        if (uploadedFile.delete()) {
            log.info("Temporary file deleted successfully.");
        } else {
            log.warn("Failed to delete temporary file: {}", uploadedFile.getAbsolutePath());
        }
    }
}
