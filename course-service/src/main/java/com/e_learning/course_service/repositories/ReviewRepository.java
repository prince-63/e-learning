package com.e_learning.course_service.repositories;

import com.e_learning.course_service.collections.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    void deleteAllByCourseId(String courseId);

    List<Review> findAllByCourseId(String courseId);

}
