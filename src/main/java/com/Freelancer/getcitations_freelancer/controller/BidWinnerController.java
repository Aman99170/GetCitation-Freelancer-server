package com.Freelancer.getcitations_freelancer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Freelancer.getcitations_freelancer.model.UserModel;
import com.Freelancer.getcitations_freelancer.service.BidWinnerService;

@RestController
@RequestMapping("/freelancer")
public class BidWinnerController {
	
	@Autowired BidWinnerService bwService;
	
	@GetMapping("/fetchWinner/{paperId}")
	public UserModel fetchWinner(@PathVariable("paperId") String paperId) {
		System.out.println("Entered into fetchWinner"+paperId);
		UserModel resp = new UserModel();
		try {
			resp = bwService.fetchWinner(paperId);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
}
