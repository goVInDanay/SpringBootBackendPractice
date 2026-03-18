package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Driver;

public interface DriverRepository extends JpaRepository<Driver, Long> {
	Optional<Driver> findByIdAndLicenseNumber(long id, String licenseNumber);
}
