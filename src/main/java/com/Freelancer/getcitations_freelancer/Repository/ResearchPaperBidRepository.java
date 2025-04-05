package com.Freelancer.getcitations_freelancer.Repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Freelancer.getcitations_freelancer.dto.BidDetails;
import com.Freelancer.getcitations_freelancer.model.ReseachPaperBiddingModel;

@Repository
public interface ResearchPaperBidRepository extends JpaRepository<ReseachPaperBiddingModel, Integer> {

	@Query("select distinct paperId from ReseachPaperBiddingModel where bidEndDate <= :currentTime")
	List<String> getEndBid(@Param("currentTime") Timestamp currentTime);

	@Query("select RP from ReseachPaperBiddingModel RP where RP.paperId =:paperId order by RP.bidAmount,RP.bidAt limit 1")
	ReseachPaperBiddingModel getWinner(@Param("paperId") String paperId);
	
	@Query("select distinct bidStartDate from ReseachPaperBiddingModel where paperId=:paperId")
	Timestamp getBidStartDate(@Param("paperId") String paperId);

	@Query("select new com.Freelancer.getcitations_freelancer.dto.BidDetails (bidBy,bidAt,bidAmount) from ReseachPaperBiddingModel r where r.paperId=:paperId and r.bidBy._id!=:bidBy order by r.bidAmount limit 1")
	BidDetails getLowestBidBySomeoneElse(@Param("paperId") String paperId,@Param("bidBy") Integer userId);

	@Query("select new com.Freelancer.getcitations_freelancer.dto.BidDetails (bidBy,bidAt,bidAmount) from ReseachPaperBiddingModel r where r.paperId=:paperId and r.bidBy._id=:bidBy order by r.bidAmount limit 1")
	BidDetails getLowestBidByLoggedInUser(@Param("paperId") String paperId,@Param("bidBy") Integer userId);

}
