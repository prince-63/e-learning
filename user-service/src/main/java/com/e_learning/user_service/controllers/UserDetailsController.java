package com.e_learning.user_service.controllers;

import com.e_learning.user_service.dto.*;
import com.e_learning.user_service.services.UserDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.e_learning.user_service.constants.ApiConstants.*;

@RestController
@AllArgsConstructor
@Tag(name = "User Details Controller", description = "Handles user profile updates and retrieval")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @Operation(method = "GET",summary = "Get User Details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully retrieved",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid detailId"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @GetMapping(GET_USER_DETAILS)
    public ResponseEntity<ResponseDTO<UserDetailsResponseDTO>> getUserDetails(
            @PathVariable String detailsId
    ) {
        UserDetailsResponseDTO userDetailsResponseDTO = userDetailsService.getUserDetails(detailsId);
        return ResponseEntity.ok(new ResponseDTO<>("Successfully retrieved user details", true, userDetailsResponseDTO));
    }

    @Operation(method = "PATCH/POST",summary = "Update and add bio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Request proceeded successfully",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid detailId"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(value= UPDATE_BIO, method = {RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<ResponseDTO<UserDetailsResponseDTO>> updateBio(
            @PathVariable Long userId,
            @Valid @RequestBody BioRequestDTO dto) {
        UserDetailsResponseDTO updated = userDetailsService.updateBio(userId, dto);
        return ResponseEntity.ok(new ResponseDTO<>("Request proceeded successfully", true, updated));
    }

    @Operation(method = "PATCH/POST",summary = "Update and add avatar")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Request proceeded successfully",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid detailId"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(value= UPDATE_AVATAR, method = {RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<ResponseDTO<UserDetailsResponseDTO>> updateAvatar(
            @PathVariable Long userId,
            @RequestPart(name = "file") MultipartFile file) {
        UserDetailsResponseDTO updated = userDetailsService.updateAvatarUrl(userId, file);
        return ResponseEntity.ok(new ResponseDTO<>("Request proceeded successfully", true, updated));
    }

    @Operation(method = "PATCH/POST",summary = "Update and add profession")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Request proceeded successfully",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid detailId"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(value= UPDATE_PROFESSION, method = {RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<ResponseDTO<UserDetailsResponseDTO>> updateProfession(
            @PathVariable Long userId,
            @Valid @RequestBody ProfessionRequestDTO dto) {
        UserDetailsResponseDTO updated = userDetailsService.updateProfession(userId, dto);
        return ResponseEntity.ok(new ResponseDTO<>("Request proceeded successfully", true, updated));
    }

    @Operation(method = "PATCH/POST",summary = "Update and add phone number")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Request proceeded successfully",
                    content = @Content(schema = @Schema(implementation = ResponseDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid detailId"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error")
    })
    @RequestMapping(value= UPDATE_PHONE, method = {RequestMethod.POST, RequestMethod.PATCH})
    public ResponseEntity<ResponseDTO<UserDetailsResponseDTO>> updatePhoneNumber(
            @PathVariable Long userId,
            @Valid @RequestBody PhoneNumberRequestDTO dto) {
        UserDetailsResponseDTO updated = userDetailsService.updatePhoneNumber(userId, dto);
        return ResponseEntity.ok(new ResponseDTO<>("Request proceeded successfully", true, updated));
    }
}
