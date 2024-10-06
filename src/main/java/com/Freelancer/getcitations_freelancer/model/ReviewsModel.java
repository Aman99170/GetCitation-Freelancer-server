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
@Table(name="reviewstable")
public class ReviewsModel {
	@Id 
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 @Column(name="Review_Id")
	 private Integer reviewId;
	 @Column(name="rating_value")
	 private Float ratingValue;
	 @Column(name="review")
	 private String review;
	 @ManyToOne
	 @JoinColumn(name = "reviewed_by", nullable = false,referencedColumnName = "_Id") 
     private UserModel reviewedBy;
	 @CreationTimestamp
	 @Column(name="reviewed_at")
	 private Timestamp reviewedAt;
	 
	 public Integer getReviewId() {
			return reviewId;
		}
		public void setReviewId(Integer reviewId) {
			this.reviewId = reviewId;
		}
		public Float getRatingValue() {
			return ratingValue;
		}
		public void setRatingValue(Float ratingValue) {
			this.ratingValue = ratingValue;
		}
		public String getReview() {
			return review;
		}
		public void setReview(String review) {
			this.review = review;
		}
		public UserModel getReviewedBy() {
			return reviewedBy;
		}
		public void setReviewedBy(UserModel reviewedBy) {
			this.reviewedBy = reviewedBy;
		}
		public Timestamp getReviewedAt() {
			return reviewedAt;
		}
		public void setReviewedAt(Timestamp reviewedAt) {
			this.reviewedAt = reviewedAt;
		}
		
		@Override
		public String toString() {
			return "ReviewsModel [reviewId=" + reviewId + ", ratingValue=" + ratingValue + ", review=" + review
					+ ", reviewedBy=" + reviewedBy + ", reviewedAt=" + reviewedAt + "]";
		}
}
