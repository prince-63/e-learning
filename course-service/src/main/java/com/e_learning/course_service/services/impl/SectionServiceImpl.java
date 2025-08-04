package com.e_learning.course_service.services.impl;

import com.e_learning.course_service.collections.Section;
import com.e_learning.course_service.dto.SectionRequestDTO;
import com.e_learning.course_service.dto.UpdateSectionRequestDTO;
import com.e_learning.course_service.exceptions.NotFoundException;
import com.e_learning.course_service.mappers.SectionMapper;
import com.e_learning.course_service.repositories.SectionRepository;
import com.e_learning.course_service.services.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;

    @Override
    public Section addSection(String courseId, SectionRequestDTO sectionRequestDTO) {
        Section section = SectionMapper.toSection(sectionRequestDTO);
        section.setCourseId(courseId);
        section.setCreatedAt(LocalDateTime.now());
        section.setUpdatedAt(LocalDateTime.now());
        return sectionRepository.save(section);
    }

    @Override
    public Section updateSection(String sectionId, UpdateSectionRequestDTO updateSectionRequestDTO) {
        Section section = loadSection(sectionId);
        section.setTitle(updateSectionRequestDTO.getTitle() != null ? updateSectionRequestDTO.getTitle() : section.getTitle());
        section.setOrder(updateSectionRequestDTO.getOrder() != 0 ? updateSectionRequestDTO.getOrder() : section.getOrder());
        section.setUpdatedAt(LocalDateTime.now());
        return sectionRepository.save(section);
    }

    private Section loadSection(String sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(() -> new NotFoundException(String.format("The requested section with section id %s not found.", sectionId)));
    }
}
