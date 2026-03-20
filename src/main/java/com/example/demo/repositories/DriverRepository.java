package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
	Optional<Driver> findByIdAndLicenseNumber(long id, String licenseNumber);
}
