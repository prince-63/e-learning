package com.e_learning.auth_service.services.impl;

import com.e_learning.auth_service.entities.User;
import com.e_learning.auth_service.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        List<GrantedAuthority> authorities = user.getRole().stream().map((role) -> new SimpleGrantedAuthority("ROLE_" + role.name())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPwd(), authorities);
    }
}
