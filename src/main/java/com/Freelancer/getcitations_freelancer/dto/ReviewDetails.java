package com.Freelancer.getcitations_freelancer.dto;

import java.sql.Timestamp;

import com.Freelancer.getcitations_freelancer.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewDetails {
	private Float ratingValue;
	private String review;
	private UserModel reviewedBy;
}
