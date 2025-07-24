package com.e_learning.auth_service.controllers;

import com.e_learning.auth_service.constaints.AuthenticationConstants;
import com.e_learning.auth_service.dto.LoginResponseDTO;
import com.e_learning.auth_service.dto.ResponseDTO;
import com.e_learning.auth_service.dto.UserLoginRequestDTO;
import com.e_learning.auth_service.dto.UserRegisterRequestDTO;
import com.e_learning.auth_service.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.e_learning.auth_service.constaints.ApiPathConstants.*;

@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(WELCOME)
    public ResponseEntity<ResponseDTO<String>> hello() {
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Hello");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(REGISTER)
    public ResponseEntity<ResponseDTO<?>> register(@RequestBody UserRegisterRequestDTO user) {
        userService.register(user);
        ResponseDTO<?> response = new ResponseDTO<>();
        response.setMessage("Successfully registered.");
        response.setSuccess(true);
        response.setData(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(LOGIN)
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> login(@RequestBody UserLoginRequestDTO user) {
        String jwtString = userService.login(user);
        ResponseDTO<LoginResponseDTO> response = new ResponseDTO<>();
        response.setData(new LoginResponseDTO(jwtString));
        response.setSuccess(true);
        response.setMessage("Successfully logged in.");
        return ResponseEntity.status(HttpStatus.OK).header(AuthenticationConstants.JWT_HEADER, jwtString).body(response);
    }

    @GetMapping(VALIDATE)
    public ResponseEntity<ResponseDTO<String>> validate(@RequestHeader(name = AuthenticationConstants.JWT_HEADER) String jwtToken) {
        boolean valid = userService.validate(jwtToken);
        if (valid) {
            ResponseDTO<String> response = new ResponseDTO<>();
            response.setSuccess(true);
            response.setMessage("Successfully validated.");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
            ResponseDTO<String> response = new ResponseDTO<>();
            response.setSuccess(false);
            response.setMessage("Invalid token.");
            response.setData(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }

    @GetMapping(REFRESH)
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> refresh(@RequestHeader(name = AuthenticationConstants.JWT_HEADER) String jwtToken) {
        String refreshToken = userService.refreshToken(jwtToken);
        ResponseDTO<LoginResponseDTO> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Successfully refreshed.");
        response.setData(new LoginResponseDTO(refreshToken));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
