package com.e_learning.course_service.services;

import com.e_learning.course_service.collections.Section;
import com.e_learning.course_service.dto.SectionRequestDTO;
import com.e_learning.course_service.dto.UpdateSectionRequestDTO;

public interface SectionService {

    Section addSection(String courseId, SectionRequestDTO sectionRequestDTO);

    Section updateSection(String sectionId, UpdateSectionRequestDTO updateSectionRequestDTO);

}
