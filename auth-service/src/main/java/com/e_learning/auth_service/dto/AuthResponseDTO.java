package com.e_learning.auth_service.dto;

import com.e_learning.auth_service.entities.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
@Schema(description = "Request body of user")
public class AuthResponseDTO {

    @Schema(description = "userId of the user")
    private Long userId;

    @Schema(description = "fullName of the user")
    private String fullName;

    @Schema(description = "email of the user")
    private String email;

    @Schema(description = "role of the user")
    private Set<Role> role;

    @Schema(description = "account status")
    private boolean active;

}
