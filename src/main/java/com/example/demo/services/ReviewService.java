package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.example.demo.models.Booking;
import com.example.demo.models.Driver;
import com.example.demo.models.Review;
import com.example.demo.repositories.BookingRepository;
import com.example.demo.repositories.DriverRepository;
import com.example.demo.repositories.ReviewRepository;

import jakarta.transaction.Transactional;

@Service
public class ReviewService {

	private final ReviewRepository reviewRepository;
	private final DriverRepository driverRepository;
	private final BookingRepository bookingRepository;

	public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository,
			DriverRepository driverRepository) {
		this.reviewRepository = reviewRepository;
		this.bookingRepository = bookingRepository;
		this.driverRepository = driverRepository;
	}

	@EventListener(ApplicationReadyEvent.class)
	@Transactional
	public void run() {
		System.out.println("**********");

		List<Booking> bookings = bookingRepository.findAll();

		bookings.stream().forEach(b -> {
			System.out.println(b);
			Review reviews = bookingRepository.findReviewByBookingId(b.getId());
			System.out.println(reviews);
		});
	}
}