package com.e_learning.auth_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Schema(description = "Represents a user in the system (Admin, Student, Instructor)")
@Data
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Schema(description = "Full name of the user", example = "John Doe", required = true)
    @NotBlank(message = "Name is required")
    private String fullName;

    @Schema(description = "Unique email address of the user", example = "john@example.com", required = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(unique = true)
    private String email;

    @Schema(description = "Password for authentication", example = "secret123", required = true)
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;

    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    @Schema(description = "Indicates if the user is active", example = "true")
    private boolean active;

    @Schema(description = "Timestamp of creation", example = "2025-07-01T11:30:00")
    @Column(updatable = false)
    @JsonIgnore
    private LocalDateTime createdAt;

}
