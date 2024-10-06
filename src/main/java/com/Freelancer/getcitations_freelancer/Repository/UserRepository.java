package com.Freelancer.getcitations_freelancer.Repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.Freelancer.getcitations_freelancer.model.UserModel;

public interface UserRepository extends JpaRepository<UserModel,Integer> {
	
	UserModel findByEmail(String email);

}
