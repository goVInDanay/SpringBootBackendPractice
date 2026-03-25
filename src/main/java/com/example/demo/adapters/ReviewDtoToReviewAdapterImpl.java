package com.example.demo.adapters;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.example.demo.dtos.ReviewDto;
import com.example.demo.models.Booking;
import com.example.demo.models.Review;
import com.example.demo.repositories.BookingRepository;

@Component
public class ReviewDtoToReviewAdapterImpl implements ReviewDtoToReviewAdapter {

	private BookingRepository bookingRepository;

	public ReviewDtoToReviewAdapterImpl(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}

	@Override
	public Review convertDto(ReviewDto reviewDto) {
		Optional<Booking> booking = bookingRepository.findById(reviewDto.getBookingId());
		if (booking.isEmpty()) {
			return null;
		}
		Review review = Review.builder().rating(reviewDto.getRating()).booking(booking.get()	)
				.content(reviewDto.getContent()).build();
		return review;
	}
}
