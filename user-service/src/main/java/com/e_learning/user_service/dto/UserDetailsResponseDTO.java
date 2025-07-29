package com.e_learning.user_service.dto;

import com.e_learning.user_service.entities.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsResponseDTO {

    @Schema(description = "Unique identifier of the user details", example = "1001")
    private String detailsId;

    @Schema(description = "Unique identifier of the user", example = "1001")
    private Long userId;

    @Schema(description = "Full name of the user", example = "John Doe")
    private String fullName;

    @Schema(description = "Email address of the user", example = "john.doe@example.com")
    private String email;

    @Schema(description = "User's short bio or description", example = "Backend developer with 5 years of experience")
    private String bio;

    @Schema(description = "Public id for identifying the url")
    private String avatarPublicId;

    @Schema(description = "URL of the user's profile picture", example = "https://cdn.app.com/avatar123.png")
    private String avatarUrl;

    @Schema(description = "User's profession or job title", example = "Software Engineer")
    private String profession;

    @Schema(description = "User's contact phone number", example = "+91-9876543210")
    private String phoneNumber;

    @Schema(description = "Roles assigned to the user", example = "[\"ROLE_USER\", \"ROLE_ADMIN\"]")
    private Set<Role> roles;

    @Schema(description = "Indicates whether the user is active", example = "true")
    private boolean active;
}
