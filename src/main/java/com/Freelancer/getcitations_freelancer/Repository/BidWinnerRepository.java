package com.Freelancer.getcitations_freelancer.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Freelancer.getcitations_freelancer.model.BidWinnerModel;

public interface BidWinnerRepository extends JpaRepository<BidWinnerModel,Integer>{
	
	BidWinnerModel findByPaperId(String paperId);
}
