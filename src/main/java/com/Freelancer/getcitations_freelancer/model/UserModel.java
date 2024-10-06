package com.Freelancer.getcitations_freelancer.model;

import java.sql.Timestamp;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name = "userstable")
public class UserModel {
@Id 
 @GeneratedValue(strategy=GenerationType.IDENTITY)
 @Column(name="_id")
 private Integer _id;
 @Column(name="Email")
 private String email;
 @Column(name="first_name")
 private String firstName;
 @Column(name="last_name")
 private String lastName;
 @Column(name="mobile_number")
 private String mobileNumber;
 @Column(name="Password")
 private String password;
 @Column(name="confirm_password")
 private String confirmPassword;
@CreationTimestamp
 @Column(name="Created_At")
 private Timestamp createdAt;
 @UpdateTimestamp
 @Column(name="Modified_At")
 private Timestamp updatedAt;
 @Column(name="Is_Active")
 private String isActive="1";
 @Column(name="user_type")
 private String userType;
 
 public Integer get_id() {
		return _id;
	}
	public void set_id(Integer _id) {
		this._id = _id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobNum) {
		this.mobileNumber = mobNum;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	 public String getConfirmPassword() {
			return confirmPassword;
		}
		public void setConfirmPassword(String confirmPassword) {
			this.confirmPassword = confirmPassword;
		}
	public Timestamp getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
	}
	public String getIsActive() {
		return isActive;
	}
	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	@Override
	public String toString() {
		return "UserModel [userId=" + _id + ", email=" + email + ", firstName=" + firstName + ", lastName="
				+ lastName + ", mobNum=" + mobileNumber + ", password=" + password + ", createdAt=" + createdAt
				+ ", modifiedAt=" + updatedAt + ", isActive=" + isActive + ", userType=" + userType +"]";
	}
}
