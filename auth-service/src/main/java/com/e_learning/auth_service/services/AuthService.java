package com.e_learning.auth_service.services;

import com.e_learning.auth_service.dto.LoginRequestDTO;
import com.e_learning.auth_service.dto.RegisterRequestDTO;
import com.e_learning.auth_service.entities.User;

public interface AuthService {

    /**
     * Register User
     * @param user - requested dto
     */
    void register(RegisterRequestDTO user);

    /**
     * Login user
     * @param user - requested dto
     * @return - a jwt string
     */
    String login(LoginRequestDTO user);

    /**
     * Validate jwtToken
     * @param jwtToken - a jwt token
     * @return - true, when jwtToken is valid otherwise false
     */
    boolean validate(String jwtToken);

    /**
     * new jwtToken
     * @param jwtToken - a jwt token
     * @return - a jwt string
     */
    String refreshToken(String jwtToken);

    /**
     * Get Auth details
     * @param userId - id of the user
     * @return - A AuthResponseDTO object containing user related details
     */
    User getAuthDetails(Long userId);

}
