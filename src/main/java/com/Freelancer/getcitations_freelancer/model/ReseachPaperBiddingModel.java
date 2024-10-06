package com.Freelancer.getcitations_freelancer.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="researchpaperbiddingtable")
public class ReseachPaperBiddingModel {
	
	@Id 
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="Bid_Id")
	 private Integer bidId;
	 @Column(name="paper_id")
	 private String paperId;
	 @Column(name="paper_name")
	 private String paperName;
	 @Column(name="paper_link")
	 private String paperLink;
	 @Column(name="paper_doi")
	 private String paperDoi;
	 @Column(name="posted_by")
	 private String postedBy;
	 @Column(name="number_Of_Citation")
	 private Integer numberOfCitation;
	 @Column(name="posted_on")
	 private Timestamp postedOn;
	 @ManyToOne
	 @JoinColumn(name = "bid_by", nullable = false,referencedColumnName = "_Id") 
     private UserModel bidBy; 
	 @CreationTimestamp
	 @Column(name="bid_at")   
	 private Timestamp bidAt;
	 @Column(name="bid_end_date") 
	 private Timestamp bidEndDate;
	@Column(name="bid_starting_date") 
	 private Timestamp bidStartDate;
	 @Column(name="bid_amount")
	 private Integer bidAmount;
	  
	public Integer getBidAmount() {
		return bidAmount;
	}
	public void setBidAmount(Integer bidAmount) {
		this.bidAmount = bidAmount;
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
		public UserModel getBidBy() {
			return bidBy;
		}
		public void setBidBy(UserModel bidBy) {
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
		
		@Override 
		public String toString() {
			return "ReseachPaperBiddingModel [bidId=" + bidId + ", paperId=" + paperId + ", paperName=" + paperName
					+ ", paperLink=" + paperLink + ", paperDoi=" + paperDoi + ", postedBy=" + postedBy
					+ ", numberOfCitation=" + numberOfCitation + ", postedOn=" + postedOn + ", bidBy=" + bidBy
					+ ", bidAt=" + bidAt + ", bidEndDate=" + bidEndDate + ", bidStartDate=" + bidStartDate
					+ ", bidAmount=" + bidAmount + "]";
		}
}
