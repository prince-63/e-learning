package com.e_learning.course_service.collections;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Schema(description = "A downloadable or viewable resource attached to a lecture")
public class Resource {

    @NotBlank
    @Schema(description = "Type of resource", example = "pdf")
    private String resourcePublicId;

    @NotBlank
    @Schema(description = "Resource URL", example = "https://cdn.example.com/notes/lecture1.pdf")
    private String resourceUrl;

}

