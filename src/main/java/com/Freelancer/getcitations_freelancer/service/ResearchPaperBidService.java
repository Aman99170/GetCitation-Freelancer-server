package com.Freelancer.getcitations_freelancer.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Freelancer.getcitations_freelancer.Repository.ResearchPaperBidRepository;
import com.Freelancer.getcitations_freelancer.dto.BidDetails;
import com.Freelancer.getcitations_freelancer.dto.RPBidStatus;
import com.Freelancer.getcitations_freelancer.model.ReseachPaperBiddingModel;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Service
public class ResearchPaperBidService {

	@Autowired
	private ResearchPaperBidRepository rpBidRepo;
	@PersistenceContext
	private EntityManager em;
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
		Timestamp res =  rpBidRepo.getBidStartDate(paperId);
		return res;
	}
	
	private List<Map<String,Object>> someOneElseUser(String paperId, Integer userId) {
		List<Map<String,Object>> resp = new ArrayList<>();
		BidDetails res = rpBidRepo.getLowestBidBySomeoneElse(paperId,userId);
		if(res!=null) {
			Map<String,Object> map = new HashMap<>();
			map.put("userDetails", res.getBidBy());
			map.put("bidAt", res.getBidAt());
			map.put("bidAmount", res.getBidAmount());
			resp.add(map);
		}
		return resp;
	}
	private List<Map<String,Object>> LoggedInUser(String paperId, Integer userId) {
		List<Map<String,Object>> resp = new ArrayList<>();
		BidDetails res = rpBidRepo.getLowestBidByLoggedInUser(paperId,userId);
		if(res!=null) {
			System.out.println(res.getBidBy());
		}
		if(res!=null) {
			Map<String,Object> map = new HashMap<>();
			map.put("userDetails", res.getBidBy());
			map.put("bidAt", res.getBidAt());
			map.put("bidAmount", res.getBidAmount());
			resp.add(map);
		}
		return resp;
	}
	
	
	public ResponseEntity<List<RPBidStatus>> fetchAllBids(Integer userId,String search,String sortBy,String status,String from,String to) {
		List<Object[]> resp =null;
		List<RPBidStatus> obj1 = new ArrayList();
		try {
			String str = "select tbl.* from (select *, (\r\n"
					+ "case\r\n"
					+ "	when now() < RP.bid_end_date then \"Bid in Progress\"\r\n"
					+ "	when bid_id in (\r\n"
					+ "		select a.bid_id from researchpaperbiddingtable a inner join bidwinnertable b\r\n"
					+ "        on a.paper_id = b.paper_id and a.bid_by=b.winner_id and a.bid_id=b.bid_id\r\n"
					+ "	) then \"won\"\r\n"
					+ "	else \"Lost\" \r\n"
					+ "end\r\n"
					+ ") as bid_status from researchpaperbiddingtable RP) as tbl where bid_by =:userId\r\n";
			String str1 = " and (tbl.paper_name like :search or tbl.paper_link like :search or tbl.paper_doi like :search)\r\n";
			String str2 = " and  tbl.bid_status =:status\r\n";
			String str3 = " and tbl.bid_at >= :from and tbl.bid_at <=:to\r\n";
			String str4 = " order by tbl.bid_at";
			String str5 = " order by tbl.number_Of_Citation desc";
			String str6 = " order by tbl.bid_amount desc";
			String str7 = " order by tbl.bid_at desc";
			if(search!=null && !search.equalsIgnoreCase("")) {
			   str = str.concat(str1);
			}
			if(status!=null && !status.equalsIgnoreCase("")) {
				   str = str.concat(str2);
			}
			if(from!=null && !from.equalsIgnoreCase("") && to!=null && !to.equalsIgnoreCase("")) {
				   str = str.concat(str3);
			}
			if(sortBy!=null && !sortBy.equalsIgnoreCase("")) {
				if(sortBy.equalsIgnoreCase("Date")) {
					str = str.concat(str4);
				}
				else if(sortBy.equalsIgnoreCase("Number of Citation")) {
					str = str.concat(str5);
				}
				else if(sortBy.equalsIgnoreCase("Amount")) {
					str = str.concat(str6);
				}  
			}
			else {
				str = str.concat(str7);
			}
			jakarta.persistence.Query query = em.createNativeQuery(str);
			query.setParameter("userId", userId);
			if(search!=null && !search.equalsIgnoreCase("")) {
				String searchPattern = "%" + search + "%";
				query.setParameter("search", searchPattern);
			}
			if(status!=null && !status.equalsIgnoreCase("")) {
				System.out.println(status+"setting status");
				query.setParameter("status", status);
			}
			if(from!=null && !from.equalsIgnoreCase("") && to!=null && !to.equalsIgnoreCase("")) {
				query.setParameter("from",from);
				query.setParameter("to",to);
			}	
			
			System.out.println(str);
			resp = query.getResultList();
			for(Object[] response : resp ) {
				RPBidStatus obj = new RPBidStatus();
				obj.setBidId((Integer)response[0]);
				obj.setPaperId((String) response[1]);
				obj.setPaperName((String) response[2]);
				obj.setPaperLink((String) response[3]);
				obj.setPaperDoi((String) response[4]);
				obj.setPostedBy((String) response[5]);
				obj.setNumberOfCitation((Integer) response[6]);
				obj.setPostedOn((Timestamp) response[7]);
				obj.setBidBy((Integer) response[8]);
				obj.setBidAt((Timestamp) response[9]);
				obj.setBidAmount((Integer) response[10]);
				obj.setBidStartDate((Timestamp) response[11]);
				obj.setBidEndDate((Timestamp) response[12]);
				obj.setBidStatus((String)response[13]);
				obj1.add(obj);
			}

		}catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.OK).body(obj1);
	}

}