package com.e_learning.user_service.services.client;

import com.e_learning.user_service.config.FeignMultipartSupportConfig;
import com.e_learning.user_service.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static com.e_learning.user_service.constants.PresignedUrlGeneratorServiceApiConstants.USER_PROFILE;

@FeignClient(
        name = "presigned-url-generator-service",
        configuration = FeignMultipartSupportConfig.class
)
public interface PresignedUrlGeneratorServiceFeignClient {

    @PostMapping(value = USER_PROFILE, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<ResponseDTO<Map<String, String>>> uploadProfileImage(
            @RequestPart("file") MultipartFile file,
            @RequestParam("userId") Long userId);

}
