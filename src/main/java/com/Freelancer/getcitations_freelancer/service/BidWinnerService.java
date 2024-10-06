package com.Freelancer.getcitations_freelancer.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Freelancer.getcitations_freelancer.Repository.BidWinnerRepository;
import com.Freelancer.getcitations_freelancer.model.BidWinnerModel;
import com.Freelancer.getcitations_freelancer.model.UserModel;

@Service
public class BidWinnerService {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/getcitation";
    static final String USER = "root";
    static final String PASS = "Belapur5#";

	@Autowired
	private BidWinnerRepository bwRepo;
	public UserModel fetchWinner(String paperId) {
		BidWinnerModel winner = bwRepo.findByPaperId(paperId);
		return winner.getWinnerId();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public void test() {
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		BigDecimal numcheck = new BigDecimal(0.00000000000);
//		try {
//		conn = DriverManager.getConnection(DB_URL, USER, PASS);
//		String str = "select value from getcitation.test";
//		pstmt = conn.prepareStatement(str);
//        rs = pstmt.executeQuery();
//        while(rs.next()) {
//        	System.out.println("Outside"+rs.getObject(1).toString());
//        	BigDecimal num = (BigDecimal)rs.getObject(1);
//       	 if(!num.equals(numcheck)) {
//       		 System.out.println("inside"+rs.getObject(1).toString());
//       	 }
//        }
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

}
