package com.e_learning.user_service.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user-details")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetails {

    @Schema(description = "Unique identifier for the user details document", example = "64ae23f9876e1234567abcde")
    @Id
    private String detailsId;

    @Schema(description = "Unique user ID linked with auth service", example = "1001", required = true)
    @NotNull(message = "User ID cannot be null")
    @Indexed(unique = true)
    private Long userId;

    @Schema(description = "User bio (max 300 characters)", example = "Software Engineer with 5 years of experience")
    @Size(max = 500, message = "Bio must not exceed 300 characters")
    private String bio;

    @Schema(description = "Public id for identifying the url")
    private String avatarPublicId;

    @Schema(description = "User's avatar image URL (must be an image)", example = "https://cdn.example.com/avatar.jpg")
    @Pattern(
            regexp = "^(http(s?):)([/|.|\\w|\\s|-])*\\.(?:jpg|gif|png|jpeg)$",
            message = "Avatar URL must be a valid image URL"
    )
    private String avatarUrl;

    @Schema(description = "User profession", example = "Backend Developer")
    @Size(max = 100, message = "Profession must not exceed 100 characters")
    private String profession;

    @Schema(description = "User phone number (10 digits only)", example = "9876543210")
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must be exactly 10 digits"
    )
    private String phoneNumber;
}
