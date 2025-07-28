package com.e_learning.user_service.controllers;

import com.e_learning.user_service.dto.*;
import com.e_learning.user_service.services.UserDetailsService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.e_learning.user_service.constants.ApiConstants.*;

@RestController
@AllArgsConstructor
@Tag(name = "User Details Controller", description = "Handles user profile updates and retrieval")
public class UserDetailsController {

    private final UserDetailsService userDetailsService;

    @GetMapping(GET_USER_DETAILS)
    public ResponseEntity<ResponseDTO<UserDetailsResponseDTO>> getUserDetails(
            @PathVariable String detailsId
    ) {
        UserDetailsResponseDTO userDetailsResponseDTO = userDetailsService.getUserDetails(detailsId);
        return ResponseEntity.ok(new ResponseDTO<>("Successfully retrieved user details", true, userDetailsResponseDTO));
    }

    @PatchMapping(UPDATE_BIO)
    public ResponseEntity<ResponseDTO<UserDetailsResponseDTO>> updateBio(
            @PathVariable Long userId,
            @Valid @RequestBody BioRequestDTO dto) {
        UserDetailsResponseDTO updated = userDetailsService.updateBio(userId, dto);
        return ResponseEntity.ok(new ResponseDTO<>("Successfully updated bio", true, updated));
    }

    @PatchMapping(value=UPDATE_AVATAR, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO<UserDetailsResponseDTO>> updateAvatar(
            @PathVariable Long userId,
            @RequestPart(name = "file") MultipartFile file) {
        UserDetailsResponseDTO updated = userDetailsService.updateAvatarUrl(userId, file);
        return ResponseEntity.ok(new ResponseDTO<>("Successfully updated avatar", true, updated));
    }

    @PatchMapping(UPDATE_PROFESSION)
    public ResponseEntity<ResponseDTO<UserDetailsResponseDTO>> updateProfession(
            @Parameter(description = "User ID") @PathVariable Long userId,
            @Valid @RequestBody ProfessionRequestDTO dto) {
        UserDetailsResponseDTO updated = userDetailsService.updateProfession(userId, dto);
        return ResponseEntity.ok(new ResponseDTO<>("Successfully updated profession", true, updated));
    }

    @PatchMapping(UPDATE_PHONE)
    public ResponseEntity<ResponseDTO<UserDetailsResponseDTO>> updatePhoneNumber(
            @Parameter(description = "User ID") @PathVariable Long userId,
            @Valid @RequestBody PhoneNumberRequestDTO dto) {
        UserDetailsResponseDTO updated = userDetailsService.updatePhoneNumber(userId, dto);
        return ResponseEntity.ok(new ResponseDTO<>("Successfully updated phone number", true, updated));
    }
}
