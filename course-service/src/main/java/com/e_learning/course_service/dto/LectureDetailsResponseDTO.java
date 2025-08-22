package com.e_learning.course_service.dto;

import com.e_learning.course_service.collections.Resource;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class LectureDetailsResponseDTO {
    private String lectureId;
    private String title;
    private int order;
    private String videoUrlPublicId;
    private String videoUrl;
    private int duration;
    private boolean preview;
    private List<Resource> resourceUrls;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
