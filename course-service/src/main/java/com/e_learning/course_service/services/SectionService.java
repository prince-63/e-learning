package com.e_learning.course_service.services;

import com.e_learning.course_service.collections.Section;
import com.e_learning.course_service.dto.SectionRequestDTO;

public interface SectionService {

    Section addSection(String courseId, SectionRequestDTO sectionRequestDTO);
}
