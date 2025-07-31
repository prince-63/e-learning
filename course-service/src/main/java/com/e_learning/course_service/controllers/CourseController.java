package com.e_learning.course_service.controllers;

import com.e_learning.course_service.collections.Course;
import com.e_learning.course_service.dto.CourseRequestDTO;
import com.e_learning.course_service.dto.CourseResponseDTO;
import com.e_learning.course_service.dto.ResponseDTO;
import com.e_learning.course_service.mappers.CourseMapper;
import com.e_learning.course_service.services.CourseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.e_learning.course_service.constants.ApiPathConstants.ADD_THUMBNAIL;
import static com.e_learning.course_service.constants.ApiPathConstants.CREATE_COURSE;

@RestController
@AllArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping(CREATE_COURSE)
    public ResponseEntity<ResponseDTO<CourseResponseDTO>> createCourse(@RequestBody @Valid CourseRequestDTO courseRequestDTO) {
        Course course = courseService.createCourse(courseRequestDTO);
        ResponseDTO<CourseResponseDTO> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Course created successfully");
        response.setData(CourseMapper.CourseToDto(course));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(ADD_THUMBNAIL)
    public ResponseEntity<ResponseDTO<CourseResponseDTO>> addThumbnail(
            @RequestParam String courseId,
            @RequestPart(name="file") MultipartFile file
    ) {
        Course course = courseService.addThumbnail(courseId, file);
        ResponseDTO<CourseResponseDTO> response = new ResponseDTO<>();
        response.setSuccess(true);
        response.setMessage("Course thumbnail added successfully");
        response.setData(CourseMapper.CourseToDto(course));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
