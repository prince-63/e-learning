package com.e_learning.course_service.services.impl;

import com.e_learning.course_service.collections.Lecture;
import com.e_learning.course_service.dto.LectureRequestDTO;
import com.e_learning.course_service.mappers.LectureMapper;
import com.e_learning.course_service.repositories.LectureRepository;
import com.e_learning.course_service.services.LectureService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;

    @Override
    public Lecture addLecture(String sectionId, LectureRequestDTO lectureRequestDTO) {
        Lecture lecture = LectureMapper.toLecture(lectureRequestDTO);
        lecture.setSectionId(sectionId);
        lecture.setCreatedAt(LocalDateTime.now());
        lecture.setUpdatedAt(LocalDateTime.now());
        return lectureRepository.save(lecture);
    }

}
