package com.e_learning.course_service.controllers;

import com.e_learning.course_service.collections.Lecture;
import com.e_learning.course_service.dto.LectureRequestDTO;
import com.e_learning.course_service.dto.LectureResponseDTO;
import com.e_learning.course_service.dto.ResponseDTO;
import com.e_learning.course_service.mappers.LectureMapper;
import com.e_learning.course_service.services.LectureService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.e_learning.course_service.constants.LectureApiConstants.ADD_LECTURE;

@RestController
@AllArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    @PostMapping(ADD_LECTURE)
    public ResponseEntity<ResponseDTO<LectureResponseDTO>> addLecture(
            @RequestParam(name="sectionId") String sectionId,
            @RequestBody @Valid LectureRequestDTO lectureRequestDTO
    ) {
        Lecture lecture = lectureService.addLecture(sectionId, lectureRequestDTO);
        ResponseDTO<LectureResponseDTO> response = new ResponseDTO<>();
        response.setMessage("Lecture Added Successfully");
        response.setSuccess(true);
        response.setData(LectureMapper.toDTO(lecture));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
