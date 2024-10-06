package com.Freelancer.getcitations_freelancer.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="bidwinnertable")
public class BidWinnerModel {
	 
	@Id 
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="bid_win_Id")
	 private Integer bidWinId;
	 @Column(name="paper_id")
	 private String paperId;
	 @ManyToOne
	 @JoinColumn(name = "winner_id", nullable = false) 
     private UserModel winnerId;
	 @Column(name="bid_id")
	 private Integer bidId;
	 
	 public Integer getBidId() {
		return bidId;
	}
	public void setBidId(Integer bidId) {
		this.bidId = bidId;
	}
	public Integer getBidWinId() {
			return bidWinId;
		}
		public void setBidWinId(Integer bidWinId) {
			this.bidWinId = bidWinId;
		}
		public String getPaperId() {
			return paperId;
		}
		public void setPaperId(String paperId) {
			this.paperId = paperId;
		}
		public UserModel getWinnerId() {
			return winnerId;
		}
		public void setWinnerId(UserModel winnerId) {
			this.winnerId = winnerId;
		}
		
		@Override
		public String toString() {
			return "BidWinnerModel [bidWinId=" + bidWinId + ", paperId=" + paperId + ", winnerId=" + winnerId
					+ ", bidId=" + bidId + "]";
		}
}
