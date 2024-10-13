package com.Freelancer.getcitations_freelancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EntityScan(basePackages = {"com.Freelancer.getcitations_freelancer.model"})
public class GetcitationsFreelancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetcitationsFreelancerApplication.class, args);
	}

}
