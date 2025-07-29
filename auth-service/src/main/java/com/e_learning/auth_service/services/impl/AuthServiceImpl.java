package com.e_learning.auth_service.services.impl;

import com.e_learning.auth_service.constaints.AuthenticationConstants;
import com.e_learning.auth_service.dto.LoginRequestDTO;
import com.e_learning.auth_service.dto.RegisterRequestDTO;
import com.e_learning.auth_service.entities.User;
import com.e_learning.auth_service.exceptions.NotFoundException;
import com.e_learning.auth_service.mappers.AuthMapper;
import com.e_learning.auth_service.repositories.UserRepository;
import com.e_learning.auth_service.services.AuthService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final Environment environment;

    @Override
    public void register(RegisterRequestDTO requestDTO) {
        if (userRepository.findByEmail(requestDTO.getEmail()).isPresent()) {
            throw new NotFoundException("User with this email already exists.");
        }

        User user = AuthMapper.toUserModel(requestDTO);
        user.setPwd(passwordEncoder.encode(requestDTO.getPwd()));
        user.setActive(true);
        userRepository.save(user);
    }

    @Override
    public String login(LoginRequestDTO request) {
        Authentication authentication = UsernamePasswordAuthenticationToken.unauthenticated(request.getEmail(), request.getPwd());
        authentication = authenticationManager.authenticate(authentication);

        if (!authentication.isAuthenticated()) {
            throw new BadCredentialsException("Invalid credentials");
        }

        String[] usernameParts = authentication.getName().split(":");
        if (usernameParts.length < 2) {
            throw new IllegalStateException("Invalid authentication name format");
        }

        String userId = usernameParts[0];
        String email = usernameParts[1];

        SecretKey secretKey = getSecretKey();

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        Date now = new Date();
        Date expiration = new Date(now.getTime() + AuthenticationConstants.ACCESS_TOKEN_EXPIRATION);

        return Jwts.builder()
                .issuer("e-learning (udemy-clone)")
                .issuedAt(now)
                .expiration(expiration)
                .claim("userId", userId)
                .claim("email", email)
                .claim("authorities", authorities)
                .signWith(secretKey)
                .compact();
    }

    @Override
    public boolean validate(String jwtToken) {
        try {
            SecretKey secretKey = getSecretKey();
            Map<String, String> detailsList = getClaims(jwtToken, secretKey);

            String authName = detailsList.get("userId") + ":" + detailsList.get("email");
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    authName,
                    null,
                    AuthorityUtils.commaSeparatedStringToAuthorityList(detailsList.get("authorities"))
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return true;
        } catch (Exception e) {
            throw new BadCredentialsException("Token validation failed");
        }
    }

    @Override
    public String refreshToken(String jwtToken) {
        try {
            SecretKey secretKey = getSecretKey();
            Map<String, String> detailsList = getClaims(jwtToken, secretKey);

            Date now = new Date();
            Date newExpiration = new Date(now.getTime() + AuthenticationConstants.ACCESS_TOKEN_EXPIRATION);

            return Jwts.builder()
                    .issuer("e-learning (udemy-clone)")
                    .issuedAt(now)
                    .expiration(newExpiration)
                    .claim("userId", detailsList.get("userId"))
                    .claim("email", detailsList.get("email"))
                    .claim("authorities", detailsList.get("authorities"))
                    .signWith(secretKey)
                    .compact();
        } catch (Exception e) {
            throw new BadCredentialsException("Token refresh failed");
        }
    }

    @Override
    public User getAuthDetails(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException(String.format("User with %d userId not found", userId)));

    }

    private SecretKey getSecretKey() {
        String secret = environment.getProperty(AuthenticationConstants.JWT_SECRET_KEY, AuthenticationConstants.JWT_SECRET_DEFAULT_VALUE);
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    private Map<String, String> getClaims(String jwtToken, SecretKey secretKey) {
        Map<String, String> detailsList = new HashMap<>();
        Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(jwtToken).getPayload();
        detailsList.put("email", claims.get("email", String.class));
        detailsList.put("userId", claims.get("userId", String.class));
        detailsList.put("authorities", claims.get("authorities", String.class));
        return detailsList;
    }
}
