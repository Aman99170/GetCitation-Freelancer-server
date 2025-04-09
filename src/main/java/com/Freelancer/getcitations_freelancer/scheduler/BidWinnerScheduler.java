package com.Freelancer.getcitations_freelancer.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.Freelancer.getcitations_freelancer.Repository.BidWinnerRepository;
import com.Freelancer.getcitations_freelancer.Repository.ResearchPaperBidRepository;
import com.Freelancer.getcitations_freelancer.model.BidWinnerModel;
import com.Freelancer.getcitations_freelancer.model.ReseachPaperBiddingModel;

import java.sql.Timestamp;
import java.util.List;

@Component
public class BidWinnerScheduler {

	@Autowired
	private BidWinnerRepository bidWinnerRepo;
	@Autowired
	private ResearchPaperBidRepository rpBidRepo;
	
	@Scheduled(cron="0 * * * * ?")
	public void EntryIntoWinnerTable() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		List<String> bidEndRPs = rpBidRepo.getEndBid(timestamp);
		for(String bidEndRP : bidEndRPs) {
			if(bidWinnerRepo.findByPaperId(bidEndRP)==null) {
				ReseachPaperBiddingModel winner = rpBidRepo.getWinner(bidEndRP);
				BidWinnerModel bidWinnerModel = new BidWinnerModel();
				bidWinnerModel.setPaperId(bidEndRP);
				bidWinnerModel.setWinnerId(winner.getBidBy());
				bidWinnerModel.setBidId(winner.getBidId());
				bidWinnerRepo.save(bidWinnerModel);
			}
		}
	}
	
}
