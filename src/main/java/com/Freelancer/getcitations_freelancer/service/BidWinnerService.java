package com.Freelancer.getcitations_freelancer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Freelancer.getcitations_freelancer.Repository.BidWinnerRepository;
import com.Freelancer.getcitations_freelancer.model.BidWinnerModel;
import com.Freelancer.getcitations_freelancer.model.UserModel;

@Service
public class BidWinnerService {

	@Autowired
	private BidWinnerRepository bwRepo;
	public UserModel fetchWinner(String paperId) {
		BidWinnerModel winner = bwRepo.findByPaperId(paperId);
		return winner.getWinnerId();
	}
	

}
