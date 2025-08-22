package com.e_learning.course_service.mappers;

import com.e_learning.course_service.collections.Lecture;
import com.e_learning.course_service.dto.LectureDetailsResponseDTO;
import com.e_learning.course_service.dto.LectureRequestDTO;
import com.e_learning.course_service.dto.LectureResponseDTO;

public class LectureMapper {

    public static Lecture toLecture(LectureRequestDTO lectureRequestDTO) {
        return Lecture.builder()
                .title(lectureRequestDTO.getTitle())
                .order(lectureRequestDTO.getOrder())
                .duration(lectureRequestDTO.getDuration())
                .build();
    }

    public static LectureResponseDTO toDTO(Lecture lecture) {
        return LectureResponseDTO.builder()
                .lectureId(lecture.getLectureId())
                .sectionId(lecture.getSectionId())
                .title(lecture.getTitle())
                .order(lecture.getOrder())
                .duration(lecture.getDuration())
                .videoUrlPublicId(lecture.getVideoUrlPublicId())
                .videoUrl(lecture.getVideoUrl())
                .preview(lecture.isPreview())
                .resourceUrls(lecture.getResourceUrls())
                .updatedAt(lecture.getUpdatedAt())
                .build();
    }

    public static LectureDetailsResponseDTO toDetailsResponseDTO(Lecture lecture) {
        return LectureDetailsResponseDTO.builder()
                .lectureId(lecture.getLectureId())
                .title(lecture.getTitle())
                .order(lecture.getOrder())
                .duration(lecture.getDuration())
                .videoUrlPublicId(lecture.getVideoUrlPublicId())
                .videoUrl(lecture.getVideoUrl())
                .preview(lecture.isPreview())
                .resourceUrls(lecture.getResourceUrls())
                .createdAt(lecture.getCreatedAt())
                .updatedAt(lecture.getUpdatedAt())
                .build();
    }
}
