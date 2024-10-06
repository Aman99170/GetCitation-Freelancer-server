package com.Freelancer.getcitations_freelancer.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Freelancer.getcitations_freelancer.Repository.ResearchPaperBidRepository;
import com.Freelancer.getcitations_freelancer.dto.RPBidStatus;
import com.Freelancer.getcitations_freelancer.model.ReseachPaperBiddingModel;
import com.Freelancer.getcitations_freelancer.model.BidWinnerModel; 
import com.Freelancer.getcitations_freelancer.model.UserModel;
import com.Freelancer.getcitations_freelancer.util.HibernateUtil;

@Service
public class ResearchPaperBidService {

	@Autowired
	private ResearchPaperBidRepository rpBidRepo;
	public void saveBid(ReseachPaperBiddingModel rpbid) {
		rpBidRepo.save(rpbid);
	}
	public Map<String,Object> fetchBidDetailsOnThisReport(String paperId,Integer userId) {
		Map<String,Object> finalResp = new HashMap<>();
		try {
			List<Map<String,Object>> loggedInUser = LoggedInUser(paperId,userId);
			List<Map<String,Object>> someOneElseUser = someOneElseUser(paperId,userId);
			Timestamp startingDate = startingDate(paperId);
			finalResp.put("loggedInUser", loggedInUser);
			finalResp.put("someOneElseUser", someOneElseUser);
			finalResp.put("startingDate", startingDate);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return finalResp;
	}
	
	private Timestamp startingDate(String paperId) {
		String str = "select distinct bidStartDate from ReseachPaperBiddingModel where paperId=:paperId";
		Timestamp resp = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(str);
		query.setParameter("paperId", paperId);
		List<Timestamp> res =  query.list();
		for(Timestamp response : res) {
			resp = (Timestamp) response;
		}
		session.close();
		return resp;
		
	}
	private List<Map<String,Object>> someOneElseUser(String paperId, Integer userId) {
		String str = "select bidBy,bidAt,bidAmount from ReseachPaperBiddingModel where paperId=:paperId and bidBy._id!=:bidBy order by bidAmount limit 1";
		List<Map<String,Object>> resp = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(str);
		query.setParameter("paperId", paperId);
		query.setParameter("bidBy", userId);
		List<Object[]> res = query.list();
		for(Object[] response :res) {
			Map<String,Object> map = new HashMap<>();
			map.put("userDetails", response[0] !=null ? response[0] : null);
			map.put("bidAt", response[1] !=null ? response[1].toString() : null);
			map.put("bidAmount", response[2] !=null ? response[2] : null);
			resp.add(map);
		}
		session.close();
		return resp;
	}
	private List<Map<String,Object>> LoggedInUser(String paperId, Integer userId) {
		String str = "select bidBy,bidAt,bidAmount from ReseachPaperBiddingModel where paperId=:paperId and bidBy._id=:bidBy order by bidAmount limit 1";
		List<Map<String,Object>> resp = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(str);
		query.setParameter("paperId", paperId);
		query.setParameter("bidBy", userId);
		List<Object[]> res = query.list();
		for(Object[] response :res) {
			Map<String,Object> map = new HashMap<>();
			map.put("userDetails", response[0] !=null ? response[0] : null);
			map.put("bidAt", response[1] !=null ? response[1].toString() : null);
			map.put("bidAmount", response[2] !=null ? response[2] : null);
			resp.add(map);
		}
		session.close();
		return resp;
	}
	public UserModel fetchWinner(String paperId) {
		BidWinnerModel resp = new BidWinnerModel();
		String str = "from BidWinnerModel b where b.paperId=:paperId";
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query query = session.createQuery(str);
		query.setParameter("paperId", paperId);
		resp = (BidWinnerModel) query.getSingleResult();
		System.out.println("Entered into fetchWinner"+resp);
		return resp.getWinnerId();
	}
	
	
	public ResponseEntity<List<RPBidStatus>> fetchAllBids(Integer userId,String search) {
		List<Object[]> resp =null;
		List<RPBidStatus> obj1 = new ArrayList();
		try {
			if(search==null || search.equalsIgnoreCase("")) {
			 resp = rpBidRepo.findByBidBy(userId);
			}
			else {
				System.out.println("search "+search);
				resp = rpBidRepo.findByBidByAndFilter(userId,search);
			}
			for(Object[] response : resp ) {
				RPBidStatus obj = new RPBidStatus();
				obj.setRpBidModel((ReseachPaperBiddingModel)response[0]);
				obj.setBidStatus((String)response[1]);
				obj1.add(obj);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(obj1);
	}

}
