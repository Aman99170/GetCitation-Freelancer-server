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

		String str = "select ratingValue,review,reviewedBy from ReviewsModel r where r.reviewedBy._id!=:userId order by ratingValue limit 4";
		List<Map<String,Object>> finalresp = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(str);
		query.setParameter("userId", userId);
		List<Object[]> res = query.list();
		for(Object[] response : res) {
			Map<String,Object> map = new HashMap<>();
			map.put("ratingValue", response[0] !=null ? response[0] : null);
			map.put("review", response[1] !=null ? response[1].toString() : null);
			map.put("userDetails", response[2] !=null ? response[2] : null);
			finalresp.add(map);
		}
		session.close();
		return finalresp;
		
	}
}
