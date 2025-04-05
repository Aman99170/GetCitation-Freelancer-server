package com.Freelancer.getcitations_freelancer.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Freelancer.getcitations_freelancer.dto.ReviewDetails;
import com.Freelancer.getcitations_freelancer.model.ReviewsModel;

public interface ReviewRepository extends JpaRepository<ReviewsModel,Integer> {

	@Query("select new com.Freelancer.getcitations_freelancer.dto.ReviewDetails (r.ratingValue,r.review,r.reviewedBy) from ReviewsModel r where r.reviewedBy._id!=:userId order by ratingValue limit 4")
	List<ReviewDetails> getAllReviews(@Param("userId") Integer userId);}
