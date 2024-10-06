package com.Freelancer.getcitations_freelancer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.Freelancer.getcitations_freelancer.Repository.UserRepository;
import com.Freelancer.getcitations_freelancer.model.UserModel;
import com.Freelancer.getcitations_freelancer.model.UserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserModel user = userRepo.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new UserPrincipal(user);
	}

}
