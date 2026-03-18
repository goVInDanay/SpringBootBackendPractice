package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "bookings" })
public class Driver extends BaseModel {

	private String name;

	@Column(nullable = false, unique = true)
	private String licenseNumber;

	private String phoneNumber;

	@OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
	private List<Booking> bookings;
}