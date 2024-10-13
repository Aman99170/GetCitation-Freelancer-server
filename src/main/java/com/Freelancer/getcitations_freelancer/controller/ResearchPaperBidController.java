package com.Freelancer.getcitations_freelancer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.Freelancer.getcitations_freelancer.dto.RPBidStatus;
import com.Freelancer.getcitations_freelancer.model.ReseachPaperBiddingModel;
import com.Freelancer.getcitations_freelancer.service.ResearchPaperBidService;

@RestController
public class ResearchPaperBidController {

	@Autowired
	private ResearchPaperBidService rpBidService;
	@PostMapping("/createBid")
	public ResponseEntity<String> createBid(@RequestBody ReseachPaperBiddingModel rpbid) {
		System.out.print(rpbid);
		try {
			rpBidService.saveBid(rpbid);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>("Bid created",HttpStatus.OK);
	}
	
	@GetMapping("/fetchBidDetailsOnThisReport/{paperId}/{userId}")
	public Map<String,Object> fetchBidDetailsOnThisReport(@PathVariable("paperId") String paperId,@PathVariable("userId")Integer userId){
		Map<String,Object> resp = new HashMap<>();
		try {
		   resp = rpBidService.fetchBidDetailsOnThisReport(paperId,userId);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	@GetMapping("/fetchAllBids/{userId}")
	public ResponseEntity<List<RPBidStatus>> fetchAllBids(@PathVariable Integer userId,@RequestParam(required=false) String search,@RequestParam(required=false) String sortBy,@RequestParam(required=false) String status,@RequestParam(required=false) String from,@RequestParam(required=false) String to){
		ResponseEntity<List<RPBidStatus>> resp = null;
		System.out.println("search"+search);
		try {
			
			resp = rpBidService.fetchAllBids(userId,search,sortBy,status,from,to);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
}
