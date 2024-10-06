package com.Freelancer.getcitations_freelancer.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.Freelancer.getcitations_freelancer.model.ReviewsModel;

public interface ReviewRepository extends JpaRepository<ReviewsModel,Integer> {}
