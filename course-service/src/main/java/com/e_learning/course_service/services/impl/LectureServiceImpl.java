package com.e_learning.course_service.services.impl;

import com.e_learning.course_service.collections.Lecture;
import com.e_learning.course_service.collections.Resource;
import com.e_learning.course_service.dto.LectureRequestDTO;
import com.e_learning.course_service.dto.UpdateLectureRequestDTO;
import com.e_learning.course_service.exceptions.NotFoundException;
import com.e_learning.course_service.mappers.LectureMapper;
import com.e_learning.course_service.repositories.LectureRepository;
import com.e_learning.course_service.services.LectureService;
import com.e_learning.course_service.services.client.PresignedUrlGeneratorClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class LectureServiceImpl implements LectureService {

    private final LectureRepository lectureRepository;
    private final PresignedUrlGeneratorClient presignedUrlGeneratorClient;

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

    @Override
    public Lecture uploadLectureVideo(MultipartFile file, Long userId, String courseId, String sectionId, String lectureId) {
        Lecture lecture = loadLecture(lectureId);

        var response = presignedUrlGeneratorClient.uploadLectureVideo(file, userId, courseId, sectionId, lectureId);

        assert response.getBody() != null;
        if (!response.getBody().getSuccess()) {
            return null;
        }

        Map<String, String> data = response.getBody().getData();
        lecture.setVideoUrl(data.get("url"));
        lecture.setVideoUrlPublicId(data.get("public_id"));
        return lectureRepository.save(lecture);
    }

    @Override
    public Lecture uploadResource(MultipartFile file, Long userId, String courseId, String sectionId, String lectureId) {
        Lecture lecture = loadLecture(lectureId);

        var response = presignedUrlGeneratorClient.uploadLectureResource(file, userId, courseId, sectionId, lectureId);
        assert response.getBody() != null;
        if (!response.getBody().getSuccess()) {
            return null;
        }
        Map<String, String> data = response.getBody().getData();
        Resource resource = new Resource();
        resource.setResourcePublicId(data.get("public_id"));
        resource.setResourceUrl(data.get("url"));
        if (lecture.getResourceUrls() != null) {
            lecture.getResourceUrls().add(resource);
        } else {
            lecture.setResourceUrls(List.of(resource));
        }
        return lectureRepository.save(lecture);
    }

    @Override
    public List<Lecture> getLectures(String sectionId) {
       return lectureRepository.findAllBySectionId(sectionId);
    }

    @Override
    public Lecture getLecture(String lectureId) {
        return loadLecture(lectureId);
    }

    @Override
    public void deleteLecture(String lectureId) {
        lectureRepository.deleteById(lectureId);
    }

    @Override
    public void deleteLecturesBySectionId(String sectionId) {
        lectureRepository.deleteAllBySectionId(sectionId);
    }

    private Lecture loadLecture(String lectureId) {
        return lectureRepository.findById(lectureId).orElseThrow(() -> new NotFoundException("Lecture with id " + lectureId + " not found."));
    }

}
