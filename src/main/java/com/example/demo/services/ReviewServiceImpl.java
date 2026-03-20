package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.models.Review;
import com.example.demo.repositories.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

	private ReviewRepository reviewRepository;

	public ReviewServiceImpl(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@Override
	public Optional<Review> findReviewById(Long id) {
		return reviewRepository.findById(id);
	}

	@Override
	public List<Review> findAllReviews() {
		// TODO Auto-generated method stub
		return reviewRepository.findAll();
	}

	@Override
	public boolean deleteReviewById(Long id) {
		// TODO Auto-generated method stub
		try {
			reviewRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
