package com.e_learning.presigned_url_generator_service.controller;

import com.e_learning.presigned_url_generator_service.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.e_learning.presigned_url_generator_service.constants.ApiConstants.WELCOME;

@RestController
public class WelcomeController {

    @GetMapping(WELCOME)
    public ResponseEntity<ResponseDTO<?>> welcome() {
        return ResponseEntity.ok(new ResponseDTO<>("Hello World", true, null));
    }

}
