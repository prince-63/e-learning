package com.e_learning.course_service.services;

import com.e_learning.course_service.collections.Course;
import com.e_learning.course_service.dto.CourseDetailsResponseDTO;
import com.e_learning.course_service.dto.CourseRequestDTO;
import com.e_learning.course_service.dto.UpdateCourseRequestDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService {

    Course createCourse(Long instructorId, CourseRequestDTO courseRequestDTO);

    Course addThumbnail(String courseId, MultipartFile file);

    Course updateCourseDetails(String courseId, UpdateCourseRequestDTO updateCourseRequestDTO);

    CourseDetailsResponseDTO getCourseDetails(String courseId);

    List<CourseDetailsResponseDTO> getAllCourseDetails();

    void deleteCourse(String courseId);

}
