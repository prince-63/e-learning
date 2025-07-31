package com.e_learning.course_service.services;

import com.e_learning.course_service.collections.Course;
import com.e_learning.course_service.dto.CourseRequestDTO;

public interface CourseService {

    Course createCourse(CourseRequestDTO courseRequestDTO);

}
