package com.Freelancer.getcitations_freelancer.dto;

import java.sql.Timestamp;

import com.Freelancer.getcitations_freelancer.model.ReseachPaperBiddingModel;
import com.Freelancer.getcitations_freelancer.model.UserModel;

public class RPBidStatus {

	
//	private ReseachPaperBiddingModel rpBidModel;
	private String bidStatus;
private Integer bidId;
private String paperId;
private String paperName;
private String paperLink;
private String paperDoi;
private String postedBy;
private Integer numberOfCitation;
private Timestamp postedOn;
private Integer bidBy;
private Timestamp bidAt;
private Timestamp bidEndDate;
private Timestamp bidStartDate;
private Integer bidAmount;
	
//	public ReseachPaperBiddingModel getRpBidModel() {
//		return rpBidModel;
//	}
//	public void setRpBidModel(ReseachPaperBiddingModel rpBidModel) {
//		this.rpBidModel = rpBidModel;
//	}
	
//	@Override
//	public String toString() {
//		return "RPBidStatus [rpBidModel=" + rpBidModel + ", bidStatus=" + bidStatus + "]";
//	}
	 
//	 public RPBidStatus(Integer bidId, String paperId, String paperName, String paperLink, String paperDoi,
//				String postedBy, Integer numberOfCitation, Timestamp postedOn, UserModel bidBy, Timestamp bidAt,
//				Timestamp bidEndDate, Timestamp bidStartDate, Integer bidAmount, String bidStatus) {
//			super();
//			this.bidId = bidId;
//			this.paperId = paperId;
//			this.paperName = paperName;
//			this.paperLink = paperLink;
//			this.paperDoi = paperDoi;
//			this.postedBy = postedBy;
//			this.numberOfCitation = numberOfCitation;
//			this.postedOn = postedOn;
//			this.bidBy = bidBy;
//			this.bidAt = bidAt;
//			this.bidEndDate = bidEndDate;
//			this.bidStartDate = bidStartDate;
//			this.bidAmount = bidAmount;
//			this.bidStatus = bidStatus;
//		}
	 
	 
	 public String getBidStatus() {
		return bidStatus;
	}
	public void setBidStatus(String bidStatus) {
		this.bidStatus = bidStatus;
	}
	public Integer getBidId() {
			return bidId;
		}
		public void setBidId(Integer bidId) {
			this.bidId = bidId;
		}
		public String getPaperId() {
			return paperId;
		}
		public void setPaperId(String paperId) {
			this.paperId = paperId;
		}
		public String getPaperName() {
			return paperName;
		}
		public void setPaperName(String paperName) {
			this.paperName = paperName;
		}
		public String getPaperLink() {
			return paperLink;
		}
		public void setPaperLink(String paperLink) {
			this.paperLink = paperLink;
		}
		public String getPaperDoi() {
			return paperDoi;
		}
		public void setPaperDoi(String paperDoi) {
			this.paperDoi = paperDoi;
		}
		public String getPostedBy() {
			return postedBy;
		}
		public void setPostedBy(String postedBy) {
			this.postedBy = postedBy;
		}
		public Integer getNumberOfCitation() {
			return numberOfCitation;
		}
		public void setNumberOfCitation(Integer numberOfCitation) {
			this.numberOfCitation = numberOfCitation;
		}
		public Timestamp getPostedOn() {
			return postedOn;
		}
		public void setPostedOn(Timestamp postedOn) {
			this.postedOn = postedOn;
		}
		public Integer getBidBy() {
			return bidBy;
		}
		public void setBidBy(Integer bidBy) {
			this.bidBy = bidBy;
		}
		public Timestamp getBidAt() {
			return bidAt;
		}
		public void setBidAt(Timestamp bidAt) {
			this.bidAt = bidAt;
		}
		public Timestamp getBidEndDate() {
			return bidEndDate;
		}
		public void setBidEndDate(Timestamp bidEndDate) {
			this.bidEndDate = bidEndDate;
		}
		public Timestamp getBidStartDate() {
			return bidStartDate;
		}
		public void setBidStartDate(Timestamp bidStartDate) {
			this.bidStartDate = bidStartDate;
		}
		public Integer getBidAmount() {
			return bidAmount;
		}
		public void setBidAmount(Integer bidAmount) {
			this.bidAmount = bidAmount;
		}
		
		@Override
		public String toString() {
			return "RPBidStatus [bidId=" + bidId + ", paperId=" + paperId + ", paperName=" + paperName + ", paperLink="
					+ paperLink + ", paperDoi=" + paperDoi + ", postedBy=" + postedBy + ", numberOfCitation="
					+ numberOfCitation + ", postedOn=" + postedOn + ", bidBy=" + bidBy + ", bidAt=" + bidAt
					+ ", bidEndDate=" + bidEndDate + ", bidStartDate=" + bidStartDate + ", bidAmount=" + bidAmount
					+ ", bidStatus=" + bidStatus + "]";
		}
	  
}
