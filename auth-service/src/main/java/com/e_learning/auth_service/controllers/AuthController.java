package com.e_learning.auth_service.controllers;

import com.e_learning.auth_service.constaints.AuthenticationConstants;
import com.e_learning.auth_service.dto.*;
import com.e_learning.auth_service.entities.User;
import com.e_learning.auth_service.mappers.AuthMapper;
import com.e_learning.auth_service.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.e_learning.auth_service.constaints.ApiPathConstants.*;

@Tag(
        name = "User Management APIs",
        description = "Endpoints for user registration, login, validate-token, and refresh-token"
)
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Welcome endpoint to check service is running")
    @ApiResponse(responseCode = "200", description = "Service is running",
            content = @Content(schema = @Schema(implementation = ResponseDTO.class)))
    @GetMapping(WELCOME)
    public ResponseEntity<ResponseDTO<String>> hello() {
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Hello");
        response.setData(null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "Register a new user (student, instructor, admin)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully registered",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping(REGISTER)
    public ResponseEntity<ResponseDTO<?>> register(@RequestBody RegisterRequestDTO user) {
        authService.register(user);
        ResponseDTO<?> response = new ResponseDTO<>();
        response.setMessage("Successfully registered.");
        response.setSuccess(true);
        response.setData(null);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Login user and get JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully logged in",
                    content = @Content(schema = @Schema(implementation = LoginResponseDTO.class))),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping(LOGIN)
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> login(@RequestBody LoginRequestDTO user) {
        String jwtString = authService.login(user);
        ResponseDTO<LoginResponseDTO> response = new ResponseDTO<>();
        response.setData(new LoginResponseDTO(jwtString));
        response.setSuccess(true);
        response.setMessage("Successfully logged in.");
        return ResponseEntity.status(HttpStatus.OK)
                .header(AuthenticationConstants.JWT_HEADER, jwtString)
                .body(response);
    }

    @Operation(summary = "Validate JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token is valid"),
            @ApiResponse(responseCode = "400", description = "Invalid token")
    })
    @GetMapping(VALIDATE)
    public ResponseEntity<ResponseDTO<String>> validate(
            @RequestHeader(name = AuthenticationConstants.JWT_HEADER) String jwtToken) {
        boolean valid = authService.validate(jwtToken);
        ResponseDTO<String> response = new ResponseDTO<>();
        response.setSuccess(valid);
        response.setMessage(valid ? "Successfully validated." : "Invalid token.");
        response.setData(null);
        return ResponseEntity.status(valid ? HttpStatus.OK : HttpStatus.BAD_REQUEST).body(response);
    }

    @Operation(summary = "Refresh JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Token refreshed successfully",
                    content = @Content(schema = @Schema(implementation = LoginResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid token")
    })
    @GetMapping(REFRESH)
    public ResponseEntity<ResponseDTO<LoginResponseDTO>> refresh(
            @RequestHeader(name = AuthenticationConstants.JWT_HEADER) String jwtToken) {
        String refreshToken = authService.refreshToken(jwtToken);
        ResponseDTO<LoginResponseDTO> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Successfully refreshed.");
        response.setData(new LoginResponseDTO(refreshToken));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @Operation(summary = "get auth details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Auth details retried successfully"),
            @ApiResponse(responseCode = "400", description = "Auth details not found")
    })
    @GetMapping(GET_AUTH_DETAILS)
    public ResponseEntity<ResponseDTO<AuthResponseDTO>> getAuthDetails(@PathVariable Long userId) {
        User user = authService.getAuthDetails(userId);
        ResponseDTO<AuthResponseDTO> response =  new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Auth details retrieved successfully.");
        response.setData(AuthMapper.toAuthResponseDTO(user));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
