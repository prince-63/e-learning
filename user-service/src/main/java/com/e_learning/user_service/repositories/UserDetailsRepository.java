package com.e_learning.user_service.repositories;

import com.e_learning.user_service.entities.UserDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends MongoRepository<UserDetails, String> {

    Optional<UserDetails> findByUserId(Long userId);

    Optional<UserDetails> findByDetailsId(String detailsId);
}
