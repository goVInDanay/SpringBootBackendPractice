package com.example.demo.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
	Integer countAllByRatingLessThanEqual(Integer givenRating);

	List<Review> findAllByRatingLessThanEqual(Integer givenRating);

	List<Review> findAllByCreatedAtBefore(LocalDateTime date);
}
