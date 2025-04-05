package com.Freelancer.getcitations_freelancer.dto;

import java.sql.Timestamp;

import com.Freelancer.getcitations_freelancer.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BidDetails {
	private UserModel bidBy;
	private Timestamp bidAt;
	private Integer bidAmount;
}
