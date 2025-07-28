package com.e_learning.user_service.services.impl;

import com.e_learning.user_service.dto.*;
import com.e_learning.user_service.entities.UserDetails;
import com.e_learning.user_service.exceptions.NotFoundException;
import com.e_learning.user_service.mappers.UserDetailsMapper;
import com.e_learning.user_service.repositories.UserDetailsRepository;
import com.e_learning.user_service.services.UserDetailsService;
import com.e_learning.user_service.services.client.AuthServiceClient;
import com.e_learning.user_service.services.client.PresignedUrlGeneratorServiceFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.util.function.Consumer;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;
    private final PresignedUrlGeneratorServiceFeignClient presignedUrlGeneratorServiceFeignClient;
    private final AuthServiceClient authServiceClient;

    @Override
    public UserDetailsResponseDTO getUserDetails(String detailsId) {
        UserDetails userDetails = userDetailsRepository.findByDetailsId(detailsId)
                .orElseThrow(() -> new NotFoundException(String.format("UserDetails with id: %s not found", detailsId)));

        var response = authServiceClient.getAuthDetails(userDetails.getUserId());
        assert response.getBody() != null;

        if (Boolean.TRUE.equals(response.getBody().getSuccess())) {
            AuthResponseDTO authResponseDTO = response.getBody().getData();
            return UserDetailsMapper.mapUserDetailsResponseDTO(userDetails, authResponseDTO);
        }

        throw new NotFoundException(String.format("Auth details not found for userId: %s", userDetails.getUserId()));
    }

    @Override
    public UserDetailsResponseDTO updateBio(Long userId, BioRequestDTO dto) {
        return updateUserDetails(userId, details -> details.setBio(dto.getBio()));
    }

    @Override
    public UserDetailsResponseDTO updateProfession(Long userId, ProfessionRequestDTO dto) {
        return updateUserDetails(userId, details -> details.setProfession(dto.getProfession()));
    }

    @Override
    public UserDetailsResponseDTO updatePhoneNumber(Long userId, PhoneNumberRequestDTO dto) {
        return updateUserDetails(userId, details -> details.setPhoneNumber(dto.getPhoneNumber()));
    }

    @Override
    public UserDetailsResponseDTO updateAvatarUrl(Long userId, MultipartFile file) {
        UserDetails userDetails = loadOrCreateUserDetails(userId);

        var response = presignedUrlGeneratorServiceFeignClient.uploadProfileImage(file, userId);
        assert response.getBody() != null;

        if (Boolean.TRUE.equals(response.getBody().getSuccess())) {
            Map<String, String> urlInfo = response.getBody().getData();
            userDetails.setAvatarPublicId(urlInfo.get("public_id"));
            userDetails.setAvatarUrl(urlInfo.get("url"));

            UserDetails updated = userDetailsRepository.save(userDetails);
            return mapWithAuth(updated);
        }

        throw new RuntimeException("Failed to upload avatar image");
    }

    private UserDetailsResponseDTO updateUserDetails(Long userId, Consumer<UserDetails> updater) {
        UserDetails userDetails = loadOrCreateUserDetails(userId);
        updater.accept(userDetails);
        UserDetails updated = userDetailsRepository.save(userDetails);
        return mapWithAuth(updated);
    }

    private UserDetails loadOrCreateUserDetails(Long userId) {
        return userDetailsRepository.findByUserId(userId).orElseGet(() -> {
            UserDetails newUserDetails = new UserDetails();
            newUserDetails.setUserId(userId);
            return newUserDetails;
        });
    }

    private UserDetailsResponseDTO mapWithAuth(UserDetails userDetails) {
        var response = authServiceClient.getAuthDetails(userDetails.getUserId());
        assert response.getBody() != null;

        if (Boolean.TRUE.equals(response.getBody().getSuccess())) {
            AuthResponseDTO authResponseDTO = response.getBody().getData();
            return UserDetailsMapper.mapUserDetailsResponseDTO(userDetails, authResponseDTO);
        }

        throw new NotFoundException("Auth details not found for userId: " + userDetails.getUserId());
    }
}
