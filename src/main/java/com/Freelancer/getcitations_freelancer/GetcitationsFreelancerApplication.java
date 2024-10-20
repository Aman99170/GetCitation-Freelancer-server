package com.Freelancer.getcitations_freelancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableScheduling
public class GetcitationsFreelancerApplication {
	

	public static void main(String[] args) {
		
//        Dotenv dotenv = Dotenv.configure().load();
        
        // Set the environment variables to System properties
//        System.setProperty("DB_URL", dotenv.get("MYSQL_ADDON_URI"));
//        System.setProperty("DB_USERNAME", dotenv.get("MYSQL_ADDON_USER"));
//        System.setProperty("DB_PASSWORD", dotenv.get("MYSQL_ADDON_PASSWORD"));
//        System.setProperty("FRONTEND_URL", dotenv.get("FRONTEND_URL"));
		SpringApplication.run(GetcitationsFreelancerApplication.class, args);
	}

}
