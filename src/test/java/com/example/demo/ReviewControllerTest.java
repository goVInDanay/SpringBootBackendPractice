package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.adapters.ReviewDtoToReviewAdapter;
import com.example.demo.controllers.ReviewController;
import com.example.demo.dtos.ReviewDto;
import com.example.demo.models.Booking;
import com.example.demo.models.Review;
import com.example.demo.services.ReviewService;

public class ReviewControllerTest {

	@InjectMocks
	private ReviewController reviewController;

	@Mock
	private ReviewService reviewService;

	@Mock
	private ReviewDtoToReviewAdapter reviewDtoToReviewAdapter;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindReviewById_Success() {
		Long reviewId = 1L;
		Review mockReview = Review.builder().build();
		mockReview.setId(reviewId);
		when(reviewService.findReviewById(reviewId)).thenReturn(Optional.of(mockReview));

		ResponseEntity<?> response = reviewController.findReviewById(reviewId);

		assertEquals(HttpStatus.OK, response.getStatusCode());

		Optional<Review> returnedReview = (Optional<Review>) response.getBody();
		assertEquals(reviewId, returnedReview.get().getId());
	}

	@Test
	public void testPublishReview_Success() {

		ReviewDto requestDto = new ReviewDto();

		Booking booking = new Booking();
		booking.setId(1L);
		requestDto.setBookingId(booking.getId());

		Review incomingReview = Review.builder().content("Test").rating(1.9).booking(booking).build();
		when(reviewDtoToReviewAdapter.convertDto(requestDto)).thenReturn(incomingReview);

		Review savedReview = Review.builder().content(incomingReview.getContent()).booking(incomingReview.getBooking())
				.rating(incomingReview.getRating()).build();

		when(reviewService.publishReview(incomingReview)).thenReturn(savedReview);

		ResponseEntity<?> response = reviewController.publishReview(requestDto);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}
}
