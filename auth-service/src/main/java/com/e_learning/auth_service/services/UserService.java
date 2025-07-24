package com.e_learning.auth_service.services;

import com.e_learning.auth_service.dto.UserLoginRequestDTO;
import com.e_learning.auth_service.dto.UserRegisterRequestDTO;

public interface UserService {

    /**
     * Register User
     * @param user - requested dto
     */
    void register(UserRegisterRequestDTO user);

    /**
     * Login user
     * @param user - requested dto
     * @return - a jwt string
     */
    String login(UserLoginRequestDTO user);

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

}
