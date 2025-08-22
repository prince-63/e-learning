package com.e_learning.course_service.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class SectionDetailsResponseDTO {
    private String sectionId;
    private String title;
    private int order;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private List<LectureDetailsResponseDTO> lectureDetails;
}
