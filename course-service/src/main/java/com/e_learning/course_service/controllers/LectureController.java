package com.e_learning.course_service.controllers;

import com.e_learning.course_service.collections.Lecture;
import com.e_learning.course_service.dto.LectureRequestDTO;
import com.e_learning.course_service.dto.LectureResponseDTO;
import com.e_learning.course_service.dto.ResponseDTO;
import com.e_learning.course_service.dto.UpdateLectureRequestDTO;
import com.e_learning.course_service.mappers.LectureMapper;
import com.e_learning.course_service.services.LectureService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import static com.e_learning.course_service.constants.LectureApiConstants.*;

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

    @PatchMapping(UPLOAD_LECTURE_VIDEO)
    public ResponseEntity<ResponseDTO<LectureResponseDTO>> updateLectureVideo(
            @RequestPart("file") MultipartFile file,
            @RequestParam("userId") Long userId,
            @RequestParam("courseId") String courseId,
            @RequestParam("sectionId") String sectionId,
            @RequestParam("lectureId") String lectureId
    ) {
        Lecture lecture = lectureService.uploadLectureVideo(file, userId, courseId, sectionId, lectureId);
        ResponseDTO<LectureResponseDTO> response = new ResponseDTO<>();
        response.setMessage("Lecture Video Uploaded Successfully");
        response.setSuccess(true);
        response.setData(LectureMapper.toDTO(lecture));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PatchMapping(UPDATE_LECTURE)
    public ResponseEntity<ResponseDTO<LectureResponseDTO>> updateLecture(
            @RequestParam(name="lectureId") String lectureId,
            @RequestBody UpdateLectureRequestDTO updateLectureRequestDTO
    ) {
        Lecture lecture = lectureService.updateLecture(lectureId, updateLectureRequestDTO);
        ResponseDTO<LectureResponseDTO> response = new ResponseDTO<>();
        response.setMessage("Lecture Updated Successfully");
        response.setSuccess(true);
        response.setData(LectureMapper.toDTO(lecture));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
