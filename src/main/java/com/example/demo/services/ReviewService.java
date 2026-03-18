package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import com.example.demo.UberReviewServiceApplication;
import com.example.demo.models.Booking;
import com.example.demo.models.Driver;
import com.example.demo.models.Review;
import com.example.demo.repositories.BookingRepository;
import com.example.demo.repositories.DriverRepository;
import com.example.demo.repositories.ReviewRepository;

@Service
public class ReviewService implements CommandLineRunner {

	private final ReviewRepository reviewRepository;
	private final DriverRepository driverRepository;
	private final BookingRepository bookingRepository;

	public ReviewService(ReviewRepository reviewRepository, BookingRepository bookingRepository,
			DriverRepository driverRepository) {
		this.reviewRepository = reviewRepository;
		this.bookingRepository = bookingRepository;
		this.driverRepository = driverRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("**********");

//		 Example: create driver first
//		Driver dri = Driver.builder().name("John").licenseNumber("D1232141").build();
//		driverRepository.save(dri);
//		System.out.println("Driver saved: " + dri);
		Optional<Driver> driver = driverRepository.findByIdAndLicenseNumber(1L, "D1232141");
		driver.ifPresent(d -> {
			System.out.println(d);
			List<Booking> bookings = bookingRepository.findAllByDriverId(d.getId());
			bookings.forEach(System.out::println);
		});
	}
}