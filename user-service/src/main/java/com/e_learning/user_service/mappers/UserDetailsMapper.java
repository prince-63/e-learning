package com.e_learning.user_service.mappers;

import com.e_learning.user_service.dto.AuthResponseDTO;
import com.e_learning.user_service.dto.UserDetailsResponseDTO;
import com.e_learning.user_service.entities.UserDetails;

public class UserDetailsMapper {

    public static UserDetailsResponseDTO mapUserDetailsResponseDTO(UserDetails userDetails, AuthResponseDTO authResponseDTO) {
        return UserDetailsResponseDTO.builder()
                .detailsId(userDetails.getDetailsId())
                .userId(userDetails.getUserId())
                .fullName(authResponseDTO.getFullName())
                .email(authResponseDTO.getEmail())
                .profession(userDetails.getProfession())
                .bio(userDetails.getBio())
                .avatarPublicId(userDetails.getAvatarPublicId())
                .avatarUrl(userDetails.getAvatarUrl())
                .phoneNumber(userDetails.getPhoneNumber())
                .roles(authResponseDTO.getRole())
                .active(authResponseDTO.isActive())
                .build();
    }
}
