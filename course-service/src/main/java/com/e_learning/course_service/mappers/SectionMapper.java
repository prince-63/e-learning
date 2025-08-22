package com.e_learning.course_service.mappers;

import com.e_learning.course_service.collections.Section;
import com.e_learning.course_service.dto.LectureDetailsResponseDTO;
import com.e_learning.course_service.dto.SectionDetailsResponseDTO;
import com.e_learning.course_service.dto.SectionRequestDTO;
import com.e_learning.course_service.dto.SectionResponseDTO;

import java.util.List;

public class SectionMapper {

    public static Section toSection(SectionRequestDTO sectionRequestDTO) {
        return Section.builder()
                .title(sectionRequestDTO.getTitle())
                .order(sectionRequestDTO.getOrder())
                .build();
    }

    public static SectionResponseDTO toDTO(Section section) {
        return SectionResponseDTO.builder()
                .sectionId(section.getSectionId())
                .title(section.getTitle())
                .order(section.getOrder())
                .courseId(section.getCourseId())
                .updatedAt(section.getUpdatedAt())
                .build();
    }

    public static SectionDetailsResponseDTO toSectionDetailsResponseDTO(Section section, List<LectureDetailsResponseDTO> lectureDetails) {
        return SectionDetailsResponseDTO
                .builder()
                .sectionId(section.getSectionId())
                .title(section.getTitle())
                .order(section.getOrder())
                .createdAt(section.getCreatedAt())
                .updatedAt(section.getUpdatedAt())
                .lectureDetails(lectureDetails)
                .build();
    }
}
