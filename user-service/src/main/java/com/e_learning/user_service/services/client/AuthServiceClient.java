package com.e_learning.user_service.services.client;

import com.e_learning.user_service.dto.AuthResponseDTO;
import com.e_learning.user_service.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static com.e_learning.user_service.constants.AuthServiceApiConstants.GET_AUTH_DETAILS;

@FeignClient("auth-service")
public interface AuthServiceClient {

    @GetMapping(GET_AUTH_DETAILS)
    ResponseEntity<ResponseDTO<AuthResponseDTO>> getAuthDetails(@PathVariable Long userId);

}
