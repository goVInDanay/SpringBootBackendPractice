package com.example.demo.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import com.example.demo.models.Review;
import com.example.demo.repositories.ReviewRepository;

@Service
public class ReviewService implements CommandLineRunner {

	private ReviewRepository reviewRepository;

	public ReviewService(ReviewRepository reviewRepository) {
		this.reviewRepository = reviewRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("**********");
		Review r = Review.builder().content("Amazing").rating(5.0).build();
		reviewRepository.save(r);
	}
}
