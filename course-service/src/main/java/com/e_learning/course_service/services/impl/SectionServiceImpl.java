package com.e_learning.course_service.services.impl;

import com.e_learning.course_service.collections.Lecture;
import com.e_learning.course_service.collections.Section;
import com.e_learning.course_service.dto.SectionRequestDTO;
import com.e_learning.course_service.dto.UpdateSectionRequestDTO;
import com.e_learning.course_service.exceptions.NotFoundException;
import com.e_learning.course_service.mappers.SectionMapper;
import com.e_learning.course_service.repositories.SectionRepository;
import com.e_learning.course_service.services.LectureService;
import com.e_learning.course_service.services.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class SectionServiceImpl implements SectionService {

    private final SectionRepository sectionRepository;
    private final LectureService lectureService;

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

    @Override
    public List<Section> getSections(String courseId) {
        return sectionRepository.findAllByCourseId(courseId);
    }

    @Override
    public List<Lecture> getLecturesBySection(String sectionId) {
        return lectureService.getLectures(sectionId);
    }

    @Override
    public void deleteSectionByCourseId(String courseId) {
        List<Section> sections = getSections(courseId);
        sections.forEach(section ->
                lectureService.deleteLecturesBySectionId(section.getSectionId())
        );
        sectionRepository.deleteAllByCourseId(courseId);
    }

    @Override
    public void deleteSectionBySectionId(String sectionId) {
        lectureService.deleteLecturesBySectionId(sectionId);
        sectionRepository.deleteById(sectionId);
    }

    private Section loadSection(String sectionId) {
        return sectionRepository.findById(sectionId).orElseThrow(() -> new NotFoundException(String.format("The requested section with section id %s not found.", sectionId)));
    }
}
