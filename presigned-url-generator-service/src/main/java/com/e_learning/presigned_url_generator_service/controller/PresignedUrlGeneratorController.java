package com.e_learning.presigned_url_generator_service.controller;

import com.e_learning.presigned_url_generator_service.dto.ResponseDTO;
import com.e_learning.presigned_url_generator_service.service.PresignedUrlGeneratorService;
import com.e_learning.presigned_url_generator_service.utils.ImageFileUtils;
import com.e_learning.presigned_url_generator_service.utils.PDFFileUtils;
import com.e_learning.presigned_url_generator_service.utils.VideoFileUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static com.e_learning.presigned_url_generator_service.constants.ApiConstants.*;

@RestController
@RequiredArgsConstructor
@Tag(name = "Presigned URL Generator", description = "APIs to upload images, videos, and PDFs to Cloudinary")
public class PresignedUrlGeneratorController {

    private final PresignedUrlGeneratorService presignedUrlService;

    @Operation(summary = "Upload Section PDF", description = "Uploads a PDF document for a course section")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "File uploaded successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input or file", content = @Content)
    })
    @PostMapping(value = COURSE_PDF, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO<Map<String, String>>> uploadSectionPdf(
            @RequestPart("file") MultipartFile file,
            @RequestParam("userId") Long userId,
            @RequestParam("courseId") Long courseId,
            @RequestParam("sectionId") Long sectionId) {
        PDFFileUtils.assertAllowed(file);
        Map<String, String> result = presignedUrlService.generateSectionPdfUrl(file, userId, courseId, sectionId);
        return ResponseEntity.ok(
                new ResponseDTO<>("PDF uploaded successfully", true, result)
        );
    }

    @Operation(summary = "Upload Course Video", description = "Uploads a video file for a course")
    @PostMapping(value = COURSE_VIDEO, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO<Map<String, String>>> uploadCourseVideo(
            @RequestPart("file") MultipartFile file,
            @RequestParam("userId") Long userId,
            @RequestParam("courseId") Long courseId) {
        VideoFileUtils.assertAllowed(file);
        Map<String, String> result = presignedUrlService.generateCourseVideoUrl(file, userId, courseId);
        return ResponseEntity.ok(
                new ResponseDTO<>("Video uploaded successfully", true, result)
        );
    }

    @Operation(summary = "Upload Course Image", description = "Uploads an image for a course thumbnail or banner")
    @PostMapping(value = COURSE_IMAGE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO<Map<String, String>>> uploadCourseImage(
            @RequestPart("file") MultipartFile file,
            @RequestParam("userId") Long userId,
            @RequestParam("courseId") Long courseId) {
        ImageFileUtils.assertAllowed(file);
        Map<String, String> result = presignedUrlService.generateCourseImageUrl(file, userId, courseId);
        return ResponseEntity.ok(
                new ResponseDTO<>("Image uploaded successfully", true, result)
        );
    }

    @Operation(summary = "Upload Profile Image", description = "Uploads a user's profile image")
    @PostMapping(value = USER_PROFILE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO<Map<String, String>>> uploadProfileImage(
            @RequestPart("file") MultipartFile file,
            @RequestParam("userId") Long userId) {
        ImageFileUtils.assertAllowed(file);
        Map<String, String> result = presignedUrlService.generateProfileImageUrl(file, userId);
        return ResponseEntity.ok(
                new ResponseDTO<>("Profile image uploaded successfully", true, result)
        );
    }
}
