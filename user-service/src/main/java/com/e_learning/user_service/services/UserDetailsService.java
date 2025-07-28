package com.e_learning.user_service.services;

import com.e_learning.user_service.dto.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * A service interface for defining user details-related operations.
 * This interface handles retrieval and updates of various user profile fields such as bio,
 * avatar URL, profession, and phone number.
 *
 * <p>Each update method is responsible for modifying a specific attribute of the user's details
 * and returning a corresponding response UserDetails to confirm the operation.</p>
 */
public interface UserDetailsService {

    /**
     * Retrieves the full user details for the specified user ID.
     *
     * @param detailsId the unique identifier of the user-details
     * @return a UserDetailsResponseDTO containing the complete user details
     */
    UserDetailsResponseDTO getUserDetails(String detailsId);

    /**
     * Updates the bio field of the user's details.
     *
     * @param userId the unique identifier of the user
     * @param dto a DTO containing the new bio content
     * @return a UserDetailsResponseDTO with the updated bio field
     */
    UserDetailsResponseDTO updateBio(Long userId, BioRequestDTO dto);

    /**
     * Updates the avatar URL field of the user's details.
     *
     * @param userId the unique identifier of the user
     * @param file a new avatar image file
     * @return a UserDetailsResponseDTO with the updated avatar URL field
     */
    UserDetailsResponseDTO updateAvatarUrl(Long userId, MultipartFile file);

    /**
     * Updates the profession field of the user's details.
     *
     * @param userId the unique identifier of the user
     * @param dto a DTO containing the new profession
     * @return a UserDetailsResponseDTO with the updated profession field
     */
    UserDetailsResponseDTO updateProfession(Long userId, ProfessionRequestDTO dto);

    /**
     * Updates the phone number field of the user's details.
     *
     * @param userId the unique identifier of the user
     * @param dto a DTO containing the new phone number
     * @return a UserDetailsResponseDTO with the updated phone number field
     */
    UserDetailsResponseDTO updatePhoneNumber(Long userId, PhoneNumberRequestDTO dto);

}
