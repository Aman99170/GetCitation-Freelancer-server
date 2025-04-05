package com.Freelancer.getcitations_freelancer.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Freelancer.getcitations_freelancer.Repository.ReviewRepository;
import com.Freelancer.getcitations_freelancer.dto.ReviewDetails;
import com.Freelancer.getcitations_freelancer.model.ReviewsModel;
import com.Freelancer.getcitations_freelancer.model.UserModel;
import com.Freelancer.getcitations_freelancer.util.HibernateUtil;

@Service
public class ReviewService {

	@Autowired 
	private ReviewRepository reviewRepo;
	
	
	public void saveUser(ReviewsModel review) {
		reviewRepo.save(review);
	}
	
	
	public List<Map<String,Object>> getAllReviews(Integer userId) {
		List<Map<String,Object>> finalresp = new ArrayList<>();
		List<ReviewDetails> res = reviewRepo.getAllReviews(userId);
		for(ReviewDetails response : res) {
			Map<String,Object> map = new HashMap<>();
			map.put("ratingValue", response.getRatingValue());
			map.put("review", response.getReview());
			map.put("userDetails", response.getReviewedBy());
			finalresp.add(map);
		}
		return finalresp;
		
	}
}
