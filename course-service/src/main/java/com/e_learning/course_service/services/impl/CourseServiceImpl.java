package com.e_learning.course_service.services.impl;

import com.e_learning.course_service.collections.Course;
import com.e_learning.course_service.collections.Section;
import com.e_learning.course_service.dto.CourseDetailsResponseDTO;
import com.e_learning.course_service.dto.CourseRequestDTO;
import com.e_learning.course_service.dto.LectureDetailsResponseDTO;
import com.e_learning.course_service.dto.UpdateCourseRequestDTO;
import com.e_learning.course_service.exceptions.NotFoundException;
import com.e_learning.course_service.mappers.CourseMapper;
import com.e_learning.course_service.mappers.LectureMapper;
import com.e_learning.course_service.mappers.SectionMapper;
import com.e_learning.course_service.repositories.CourseRepository;
import com.e_learning.course_service.services.CourseService;
import com.e_learning.course_service.services.LectureService;
import com.e_learning.course_service.services.SectionService;
import com.e_learning.course_service.services.client.PresignedUrlGeneratorClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final PresignedUrlGeneratorClient urlGeneratorClient;
    private final SectionService sectionService;
    private final LectureService lectureService;

    @Override
    public Course createCourse(Long instructorId, CourseRequestDTO courseRequestDTO) {
        Course course = CourseMapper.toCourse(courseRequestDTO);
        course.setInstructorId(instructorId);
        course.setCreatedAt(LocalDateTime.now());
        return courseRepository.save(course);
    }

    @Override
    public Course addThumbnail(String courseId, MultipartFile file) {
        Course course = loadCourse(courseId);

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

    @Override
    public Course updateCourseDetails(String courseId, UpdateCourseRequestDTO courseUpdateRequestDTO) {
        Course course = loadCourse(courseId);

        course.setTitle(courseUpdateRequestDTO.getTitle() != null ? courseUpdateRequestDTO.getTitle() : course.getTitle());
        course.setSlug(courseUpdateRequestDTO.getSlug() != null ? courseUpdateRequestDTO.getSlug() : course.getSlug());
        course.setDescription(courseUpdateRequestDTO.getDescription() != null ? courseUpdateRequestDTO.getDescription() : course.getDescription());
        course.setCategory(courseUpdateRequestDTO.getCategory() != null ? courseUpdateRequestDTO.getCategory() : course.getCategory());
        course.setSubcategory(courseUpdateRequestDTO.getSubcategory() != null ? courseUpdateRequestDTO.getSubcategory() : course.getSubcategory());
        course.setTags(courseUpdateRequestDTO.getTags() != null ? courseUpdateRequestDTO.getTags() : course.getTags());
        course.setLanguage(courseUpdateRequestDTO.getLanguage() != null ? courseUpdateRequestDTO.getLanguage() : course.getLanguage());
        course.setLevel(courseUpdateRequestDTO.getLevel() != null ? courseUpdateRequestDTO.getLevel() : course.getLevel());
        course.setPrice(courseUpdateRequestDTO.getPrice() != 0.0 ? courseUpdateRequestDTO.getPrice() : course.getPrice());
        course.setDiscountedPrice(courseUpdateRequestDTO.getDiscountedPrice() != 0.0 ? courseUpdateRequestDTO.getDiscountedPrice() : course.getDiscountedPrice());

        return courseRepository.save(course);
    }

    @Override
    public CourseDetailsResponseDTO getCourseDetails(String courseId) {
        Course course = loadCourse(courseId);
        List<Section> sections = sectionService.getSections(courseId);

        if (!sections.isEmpty()) {
            var sectionList = sections.stream().map((section) -> {
                var lectures = sectionService.getLecturesBySection(section.getSectionId());
                if (!lectures.isEmpty()) {
                    List<LectureDetailsResponseDTO> lectureDetailsResponseDTOList = lectures.stream().map(LectureMapper::toDetailsResponseDTO).toList();
                    return SectionMapper.toSectionDetailsResponseDTO(section, lectureDetailsResponseDTOList);
                } else {
                    return SectionMapper.toSectionDetailsResponseDTO(section, List.of());
                }
            }).toList();
            return CourseMapper.toCourseDetailsResponseDTO(course, sectionList);
        }

        return CourseMapper.toCourseDetailsResponseDTO(course, List.of());
    }

    @Override
    public void deleteCourse(String courseId) {
        sectionService.deleteSectionByCourseId(courseId);
        courseRepository.deleteById(courseId);
    }

    private Course loadCourse(String courseId) {
        return courseRepository.findById(courseId).orElseThrow(() -> new NotFoundException("Course with id: " + courseId + " not found"));
    }

}
