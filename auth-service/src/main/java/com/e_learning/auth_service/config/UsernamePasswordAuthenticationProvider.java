package com.e_learning.auth_service.config;

import com.e_learning.auth_service.exceptions.ConflictException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UsernamePasswordAuthenticationProvider implements AuthenticationProvider {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException, ConflictException {
        String email = authentication.getName();
        String pwd = authentication.getCredentials().toString();
        UserDetails user = userDetailsService.loadUserByUsername(email);
        if (!passwordEncoder.matches(pwd, user.getPassword())) {
            throw new ConflictException("Password does not match!");
        }
        return new UsernamePasswordAuthenticationToken(email, pwd, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
