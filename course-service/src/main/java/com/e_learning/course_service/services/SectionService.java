package com.e_learning.course_service.services;

import com.e_learning.course_service.collections.Lecture;
import com.e_learning.course_service.collections.Section;
import com.e_learning.course_service.dto.SectionRequestDTO;
import com.e_learning.course_service.dto.UpdateSectionRequestDTO;

import java.util.List;

public interface SectionService {

    Section addSection(String courseId, SectionRequestDTO sectionRequestDTO);

    Section updateSection(String sectionId, UpdateSectionRequestDTO updateSectionRequestDTO);

    List<Section> getSections(String courseId);

    List<Lecture> getLecturesBySection(String sectionId);

    void deleteSectionByCourseId(String courseId);

    void deleteSectionBySectionId(String sectionId);

}
