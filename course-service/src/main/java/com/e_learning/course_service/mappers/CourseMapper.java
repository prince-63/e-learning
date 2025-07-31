package com.e_learning.course_service.mappers;

import com.e_learning.course_service.collections.Course;
import com.e_learning.course_service.dto.CourseRequestDTO;
import com.e_learning.course_service.dto.CourseResponseDTO;

public class CourseMapper {

    public static Course DtoToCourse(CourseRequestDTO courseRequestDTO) {
        return Course.builder()
                .title(courseRequestDTO.getTitle())
                .description(courseRequestDTO.getDescription())
                .slug(courseRequestDTO.getSlug())
                .category(courseRequestDTO.getCategory())
                .subcategory(courseRequestDTO.getSubcategory())
                .tags(courseRequestDTO.getTags())
                .language(courseRequestDTO.getLanguage())
                .level(courseRequestDTO.getLevel())
                .price(courseRequestDTO.getPrice())
                .discountedPrice(courseRequestDTO.getDiscountedPrice())
                .instructorId(courseRequestDTO.getInstructorId())
                .build();
    }

    public static CourseResponseDTO  CourseToDto(Course course) {
        return CourseResponseDTO.builder()
                .courseId(course.getCourseId())
                .title(course.getTitle())
                .description(course.getDescription())
                .slug(course.getSlug())
                .category(course.getCategory())
                .subcategory(course.getSubcategory())
                .tags(course.getTags())
                .language(course.getLanguage())
                .level(course.getLevel())
                .price(course.getPrice())
                .thumbnailPublicId(course.getThumbnailPublicId())
                .thumbnailUrl(course.getThumbnailUrl())
                .discountedPrice(course.getDiscountedPrice())
                .instructorId(course.getInstructorId())
                .published(course.isPublished())
                .build();
    }
}
