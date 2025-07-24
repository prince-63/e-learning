package com.e_learning.presigned_url_generator_service.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Service for generating public URLs and public IDs for uploading media content (images, videos, PDFs) to Cloudinary.
 * Supports structured folder organization based on user, course, and section identifiers.
 */
public interface PresignedUrlGeneratorService {

    /**
     * Generates a Cloudinary URL for uploading a section-related PDF (e.g., notes, resources).
     *
     * @param file      the uploaded PDF file
     * @param userId    the ID of the user uploading the file
     * @param courseId  the course to which this section belongs
     * @param sectionId the section for which the file is being uploaded
     * @return a map containing the public_id and secure URL of the uploaded file
     */
    Map<String, String> generateSectionPdfUrl(MultipartFile file, Long userId, Long courseId, Long sectionId);

    /**
     * Generates a Cloudinary URL for uploading a course-related video.
     *
     * @param file     the uploaded video file
     * @param userId   the ID of the user uploading the video
     * @param courseId the course to which the video belongs
     * @return a map containing the public_id and secure URL of the uploaded video
     */
    Map<String, String> generateCourseVideoUrl(MultipartFile file, Long userId, Long courseId);

    /**
     * Generates a Cloudinary URL for uploading a course-related image (e.g., thumbnail, banner).
     *
     * @param file     the uploaded image file
     * @param userId   the ID of the user uploading the image
     * @param courseId the course to which the image belongs
     * @return a map containing the public_id and secure URL of the uploaded image
     */
    Map<String, String> generateCourseImageUrl(MultipartFile file, Long userId, Long courseId);

    /**
     * Generates a Cloudinary URL for uploading a user’s profile image.
     *
     * @param file   the uploaded profile image file
     * @param userId the ID of the user uploading the profile image
     * @return a map containing the public_id and secure URL of the uploaded image
     */
    Map<String, String> generateProfileImageUrl(MultipartFile file, Long userId);
}
