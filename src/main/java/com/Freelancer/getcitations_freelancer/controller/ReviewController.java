package com.Freelancer.getcitations_freelancer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Freelancer.getcitations_freelancer.model.ReviewsModel;
import com.Freelancer.getcitations_freelancer.model.UserModel;
import com.Freelancer.getcitations_freelancer.service.ReviewService;

@RestController
@RequestMapping("/freelancer")
public class ReviewController {

	@Autowired 
	private ReviewService reviewService;
	@PostMapping("/createReview")
	public ResponseEntity<String> CreateReview(@RequestBody ReviewsModel review) {
		Boolean isError=false;
		try {
			reviewService.saveUser(review);
		} catch (IllegalStateException e) {
			e.printStackTrace();
			isError=true;
		} 
		if(isError) {
			return new ResponseEntity<>("Error in creating review",HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Review created",HttpStatus.OK);
	}
	
	@GetMapping("/fetchReviewWithUserDetails/{userId}")
	public List<Map<String,Object>> GetAllReview(@PathVariable("userId") Integer userId){
		System.out.println("Enter into review");
		List<Map<String,Object>> resp = new ArrayList<>();
		try {
			resp = reviewService.getAllReviews(userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
}
