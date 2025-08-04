package com.e_learning.course_service.mappers;

import com.e_learning.course_service.collections.Section;
import com.e_learning.course_service.dto.SectionRequestDTO;
import com.e_learning.course_service.dto.SectionResponseDTO;

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
}
