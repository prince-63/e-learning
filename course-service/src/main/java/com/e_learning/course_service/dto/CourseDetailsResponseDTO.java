package com.e_learning.course_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CourseDetailsResponseDTO {
    private String courseId;
    private String title;
    private String description;
    private String slug;
    private String category;
    private String subcategory;
    private List<String> tags;
    private String language;
    private String level;
    private String thumbnailPublicId;
    private String thumbnailUrl;
    private double price;
    private double discountedPrice;
    private Long instructorId;
    private boolean published;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<SectionDetailsResponseDTO> sectionDetails;
}
