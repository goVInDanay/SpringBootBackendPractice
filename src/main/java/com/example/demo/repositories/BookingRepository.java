package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Booking;
import com.example.demo.models.Review;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("select r from Review r where r.booking.id = :bookingId")
	Review findReviewByBookingId(Long bookingId);
}