package com.e_learning.course_service.services.impl;

import com.e_learning.course_service.collections.Course;
import com.e_learning.course_service.dto.CourseRequestDTO;
import com.e_learning.course_service.exceptions.NotFoundException;
import com.e_learning.course_service.mappers.CourseMapper;
import com.e_learning.course_service.repositories.CourseRepository;
import com.e_learning.course_service.services.CourseService;
import com.e_learning.course_service.services.client.PresignedUrlGeneratorClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final PresignedUrlGeneratorClient urlGeneratorClient;

    @Override
    public Course createCourse(CourseRequestDTO courseRequestDTO) {
        Course course = CourseMapper.DtoToCourse(courseRequestDTO);
        course.setCreatedAt(LocalDateTime.now());
        return courseRepository.save(course);
    }

    @Override
    public Course addThumbnail(String courseId, MultipartFile file) {
        Course course = courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course with id: " + courseId + " not found"));

        var response = urlGeneratorClient.uploadCourseImage(file, course.getInstructorId(), courseId);

        assert response.getBody() != null;
        try {
            Map<String, String> map = response.getBody().getData();
            course.setThumbnailPublicId(map.get("public_id"));
            course.setThumbnailUrl(map.get("url"));
            return courseRepository.save(course);
        }
        catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
