package com.e_learning.user_service.services.impl;

import com.e_learning.user_service.dto.*;
import com.e_learning.user_service.repositories.UserDetailsRepository;
import com.e_learning.user_service.services.UserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDetailsRepository userDetailsRepository;

    @Override
    public UserDetailsDTO getUserDetails(Long userId) {
        return null;
    }

    @Override
    public BioResponseDTO updateBio(Long userId, BioRequestDTO dto) {
        return null;
    }

    @Override
    public AvatarResponseDTO updateAvatarUrl(Long userId, AvatarRequestDTO dto) {
        return null;
    }

    @Override
    public ProfessionResponseDTO updateProfession(Long userId, ProfessionRequestDTO dto) {
        return null;
    }

    @Override
    public PhoneNumberResponseDTO updatePhoneNumber(Long userId, PhoneNumberRequestDTO dto) {
        return null;
    }
}
