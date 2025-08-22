package com.e_learning.course_service.repositories;

import com.e_learning.course_service.collections.Lecture;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository extends MongoRepository<Lecture, String> {
    List<Lecture> findAllBySectionId(String sectionId);
}
