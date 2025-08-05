package com.e_learning.course_service.services;

import com.e_learning.course_service.collections.Lecture;
import com.e_learning.course_service.dto.LectureRequestDTO;
import com.e_learning.course_service.dto.UpdateLectureRequestDTO;
import org.springframework.web.multipart.MultipartFile;

public interface LectureService {

    Lecture addLecture(String sectionId, LectureRequestDTO lectureRequestDTO);

    Lecture updateLecture(String lectureId, UpdateLectureRequestDTO updateLectureRequestDTO);

    Lecture uploadLectureVideo(MultipartFile file, Long userId, String courseId, String sectionId, String lectureId);

}
