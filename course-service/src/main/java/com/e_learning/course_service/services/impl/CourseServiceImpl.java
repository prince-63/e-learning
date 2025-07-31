package com.e_learning.course_service.services.impl;

import com.e_learning.course_service.collections.Course;
import com.e_learning.course_service.dto.CourseRequestDTO;
import com.e_learning.course_service.mappers.CourseMapper;
import com.e_learning.course_service.repositories.CourseRepository;
import com.e_learning.course_service.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;

    @Override
    public Course createCourse(CourseRequestDTO courseRequestDTO) {
        Course course = CourseMapper.DtoToCourse(courseRequestDTO);
        course.setCreatedAt(LocalDateTime.now());
        return courseRepository.save(course);
    }

}
