package com.Freelancer.getcitations_freelancer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Freelancer.getcitations_freelancer.Repository.UserRepository;
import com.Freelancer.getcitations_freelancer.model.UserModel;
import com.Freelancer.getcitations_freelancer.service.JWTService;
import com.Freelancer.getcitations_freelancer.service.UserService;
import com.Freelancer.getcitations_freelancer.util.JSONParserUtil;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.servlet.http.HttpServletRequest;

//@CrossOrigin(
//	    origins = {
//	        "http://localhost:3000",
//	        }
//	)
@RestController
@RequestMapping("/freelancer")
public class UserController {
	
	@Autowired    
	private UserService userService; 
	
	@Autowired    
	private UserRepository userRepo;
	
	@Autowired
	private JWTService jwtservice;
	
	public UserController(UserService userService,UserRepository userRepo) {
		this.userRepo=userRepo;
		this.userService=userService;
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> CreateFreelancerUser(@RequestBody UserModel user) {
		UserModel dbuser = userRepo.findByEmail(user.getEmail());
		if(dbuser!=null) {
			return new ResponseEntity<>("Email Already Exist",HttpStatus.BAD_REQUEST);
		}
		try {
			userService.saveUser(user);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} 
		return new ResponseEntity<>("User created",HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public Map<String,String> login(@RequestBody UserModel user) {
		String authtoken = userService.verify(user);
		Map<String,String> mp = new HashMap<>();
		mp.put("authToken", authtoken);
		return mp;
	}
	
	
	@GetMapping("/getAllFreelancers")
	public List<UserModel> GetAllFreelancerUser() {
		List<UserModel> user = new ArrayList<>();
		try {
			user = userService.getAllFreelancers();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} 
		return user;
	}
	
	@PostMapping("/getUser")
    public ResponseEntity<UserModel> getUserDetailsFromToken(HttpServletRequest request) {
		String authHeaderObj = request.getHeader("Authorization");
		if (authHeaderObj == null || !authHeaderObj.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
		String authHeaderObjwithoutBearer ="";
		if(authHeaderObj!=null) {
		 authHeaderObjwithoutBearer = authHeaderObj.substring(7);
		}
		JSONParserUtil parser = new JSONParserUtil();
		String token=null;
		try {
	    JsonNode jsonAuthHeader = parser.parseStringToJson(authHeaderObjwithoutBearer);
	    token = jsonAuthHeader.get("authToken").asText();
		}catch(Exception e) {
			System.out.println("Error in parsing in getUser");
		}
        String userEmail = jwtservice.getUserEmailFromToken(token);

        UserModel user = userService.getUserByUserEmail(userEmail);

        return ResponseEntity.ok(user);
    }
	
	@PutMapping("/updateUserDetails/{userId}")
	public ResponseEntity<UserModel> updateUser(@PathVariable Integer userId,@RequestBody UserModel user){
		ResponseEntity<UserModel> resp = null;
		try {
			resp = userService.updateUser(userId,user);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return resp;
	}
	
	@PatchMapping("/updateUserPassword/{userId}")
	public Map<String,String> updateUserPassword(@PathVariable Integer userId,@RequestBody Map<String, String> passwordMap){
		Boolean resp = null;
		Map<String,String> response = new HashMap();
		String oldPassword = passwordMap.get("oldPassword");
        String newPassword = passwordMap.get("newPassword");
		try {
			resp = userService.updateUserPassword(userId,oldPassword,newPassword);
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(resp) {
			response.put("message", "Password Updated Successfully");
			return response;
		}
		response.put("message", "Error in Updating Password");
		return response;
	}
	
//	@GetMapping("/csrf-token")
//	public CsrfToken getCsrfToken(HttpServletRequest request) {
//		return (CsrfToken) request.getAttribute("_csrf");
//	}
}
