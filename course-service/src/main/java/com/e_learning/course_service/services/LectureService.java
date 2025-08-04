package com.e_learning.course_service.services;

import com.e_learning.course_service.collections.Lecture;
import com.e_learning.course_service.dto.LectureRequestDTO;

public interface LectureService {

    Lecture addLecture(String sectionId, LectureRequestDTO lectureRequestDTO);

}
