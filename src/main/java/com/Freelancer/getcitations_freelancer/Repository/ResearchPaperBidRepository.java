package com.Freelancer.getcitations_freelancer.Repository;


import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Freelancer.getcitations_freelancer.model.ReseachPaperBiddingModel;
import com.Freelancer.getcitations_freelancer.model.UserModel;

@Repository
public interface ResearchPaperBidRepository extends JpaRepository<ReseachPaperBiddingModel,Integer> {
	
	@Query("select distinct paperId from ReseachPaperBiddingModel where bidEndDate <= :currentTime")
	List<String> getEndBid(@Param("currentTime")Timestamp currentTime);
	
	@Query("select RP from ReseachPaperBiddingModel RP where RP.paperId =:paperId order by RP.bidAmount,RP.bidAt limit 1")
	ReseachPaperBiddingModel getWinner(@Param("paperId")String paperId);
	
	@Query("select RP, (\r\n"
			+ "case\r\n"
			+ "	when now() < bidEndDate then \"Bid in Progress.\"\r\n"
			+ "	when bidId in (\r\n"
			+ "		select a.bidId from ReseachPaperBiddingModel a inner join BidWinnerModel b\r\n"
			+ "        on a.paperId = b.paperId and a.bidBy=b.winnerId and a.bidId = b.bidId\r\n"
			+ "	) then \"won\"\r\n"
			+ "	else \"Lost\" \r\n"
			+ "end\r\n"
			+ ") as bidStatus from ReseachPaperBiddingModel RP where RP.bidBy._id =:userId and (RP.paperName like %:search% or RP.paperLink like %:search% or RP.paperDoi like %:search%)")
	List<Object[]> findByBidByAndFilter(Integer userId,String search);
	
	@Query("select RP, (\r\n"
			+ "case\r\n"
			+ "	when now() < bidEndDate then \"Bid in Progress.\"\r\n"
			+ "	when bidId in (\r\n"
			+ "		select a.bidId from ReseachPaperBiddingModel a inner join BidWinnerModel b\r\n"
			+ "        on a.paperId = b.paperId and a.bidBy=b.winnerId and a.bidId = b.bidId\r\n"
			+ "	) then \"won\"\r\n"
			+ "	else \"Lost\" \r\n"
			+ "end\r\n"
			+ ") as bidStatus from ReseachPaperBiddingModel RP where RP.bidBy._id =:userId")
	List<Object[]> findByBidBy(Integer userId);
	
	
}

