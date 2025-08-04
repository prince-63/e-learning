package com.e_learning.course_service.services.impl;

import com.e_learning.course_service.collections.Lecture;
import com.e_learning.course_service.dto.LectureRequestDTO;
import com.e_learning.course_service.dto.UpdateLectureRequestDTO;
import com.e_learning.course_service.exceptions.NotFoundException;
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

    @Override
    public Lecture updateLecture(String lectureId, UpdateLectureRequestDTO updateLectureRequestDTO) {
        Lecture lecture = loadLecture(lectureId);
        lecture.setTitle(updateLectureRequestDTO.getTitle() != null ? updateLectureRequestDTO.getTitle() : lecture.getTitle());
        lecture.setOrder(updateLectureRequestDTO.getOrder() != 0 ? updateLectureRequestDTO.getOrder() : lecture.getOrder());
        lecture.setDuration(updateLectureRequestDTO.getDuration() != 0 ? updateLectureRequestDTO.getDuration() : lecture.getDuration());
        lecture.setUpdatedAt(LocalDateTime.now());
        return lectureRepository.save(lecture);
    }

    private Lecture loadLecture(String lectureId) {
        return lectureRepository.findById(lectureId).orElseThrow(() -> new NotFoundException("Lecture with id " + lectureId + " not found."));
    }

}
