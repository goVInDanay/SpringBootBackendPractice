package com.example.demo.adapters;

import com.example.demo.dtos.ReviewDto;
import com.example.demo.models.Review;

public interface ReviewDtoToReviewAdapter {
	public Review convertDto(ReviewDto reviewDto);
}
