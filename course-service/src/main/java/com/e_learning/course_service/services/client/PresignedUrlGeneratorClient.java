package com.e_learning.course_service.services.client;

import com.e_learning.course_service.config.FeignMultipartSupportConfig;
import com.e_learning.course_service.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static com.e_learning.course_service.constants.PresignedUrlGeneratorClientApiConstant.COURSE_IMAGE;

@FeignClient(name = "presigned-url-generator-service", configuration = FeignMultipartSupportConfig.class)
public interface PresignedUrlGeneratorClient {

    @PostMapping(value = COURSE_IMAGE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ResponseDTO<Map<String, String>>> uploadCourseImage(
            @RequestPart("file") MultipartFile file,
            @RequestParam("userId") Long userId,
            @RequestParam("courseId") String courseId
    );

}
