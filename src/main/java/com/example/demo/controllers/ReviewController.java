package com.example.demo.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.adapters.ReviewDtoToReviewAdapter;
import com.example.demo.dtos.ReviewDto;
import com.example.demo.models.Review;
import com.example.demo.services.ReviewService;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
	private ReviewService reviewService;
	private ReviewDtoToReviewAdapter reviewDtoToReviewAdapter;

	public ReviewController(ReviewService reviewService, ReviewDtoToReviewAdapter reviewDtoToReviewAdapter) {
		this.reviewService = reviewService;
		this.reviewDtoToReviewAdapter = reviewDtoToReviewAdapter;
	}

	@PostMapping
	public ResponseEntity<?> publishReview(@RequestBody ReviewDto request) {
		Review incoming = this.reviewDtoToReviewAdapter.convertDto(request);
		if (incoming == null) {
			return new ResponseEntity<>("Invalid Arguments", HttpStatus.BAD_REQUEST);
		}
		Review review = reviewService.publishReview(incoming);
		ReviewDto response = ReviewDto.builder().id(review.getId()).content(review.getContent())
				.bookingId(review.getBooking().getId()).rating(review.getRating()).createdAt(review.getCreatedAt())
				.updatedAt(review.getUpdatedAt()).build();
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<List<Review>> getAllReviews() {
		List<Review> reviews = reviewService.findAllReviews();
		return new ResponseEntity<List<Review>>(reviews, HttpStatus.OK);
	}

	@GetMapping("/{reviewId}")
	public ResponseEntity<?> findReviewById(@PathVariable Long reviewId) {
		try {
			Optional<Review> review = this.reviewService.findReviewById(reviewId);
			return new ResponseEntity<>(review, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
