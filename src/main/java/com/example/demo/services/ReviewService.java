package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import com.example.demo.models.Review;

public interface ReviewService {

	public Review publishReview(Review review);

	public Optional<Review> findReviewById(Long id);

	public List<Review> findAllReviews();

	public boolean deleteReviewById(Long id);
}
