package com.e_learning.course_service.controllers;

import com.e_learning.course_service.collections.Section;
import com.e_learning.course_service.dto.ResponseDTO;
import com.e_learning.course_service.dto.SectionRequestDTO;
import com.e_learning.course_service.dto.SectionResponseDTO;
import com.e_learning.course_service.mappers.SectionMapper;
import com.e_learning.course_service.services.SectionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.e_learning.course_service.constants.SectionApiConstants.ADD_SECTION;

@RestController
@AllArgsConstructor
public class SectionController {

    private final SectionService sectionService;

    @PostMapping(ADD_SECTION)
    public ResponseEntity<ResponseDTO<SectionResponseDTO>> createSection(
            @RequestParam(name="courseId") String courseId,
            @RequestBody SectionRequestDTO sectionRequestDTO
    ) {
        Section section = sectionService.addSection(courseId, sectionRequestDTO);
        ResponseDTO<SectionResponseDTO> response = new ResponseDTO<>();
        response.setMessage("Section added successfully");
        response.setSuccess(true);
        response.setData(SectionMapper.toDTO(section));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
