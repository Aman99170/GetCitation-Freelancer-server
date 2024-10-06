package com.Freelancer.getcitations_freelancer.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.Freelancer.getcitations_freelancer.Repository.UserRepository;
import com.Freelancer.getcitations_freelancer.model.UserModel;
import com.Freelancer.getcitations_freelancer.util.HibernateUtil;
@Service
public class UserService {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/getcitation";
    static final String USER = "root";
    static final String PASS = "Belapur5#";

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JWTService jwt;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
	public void saveUser(UserModel user) {
		String encryptedPassword = encoder.encode(user.getPassword());
	    user.setPassword(encryptedPassword);
	    user.setConfirmPassword(encryptedPassword);
		userRepo.save(user);	
	}
	
	
	public List<UserModel> getAllFreelancers(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<UserModel> finalresp = new ArrayList<>();
		try {
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		String str = "select _id,password,Email,first_name,last_name,mobile_number,confirm_password,Created_At,Modified_At,Is_Active from userstable";
		 pstmt = conn.prepareStatement(str);
         rs = pstmt.executeQuery();
//		Session session = HibernateUtil.getSessionFactory().openSession();
//		Query query = session.createQuery(str);
//		List<Object[]> resp = query.list();
//		for(Object[] response: resp) {
//			Map<String,String> map = new HashMap<>();
//			map.put("_id", response[0] !=null ? response[0].toString() : null);
//			map.put("password", response[1] !=null ? response[1].toString() : null);
//			map.put("email", response[2] !=null ? response[2].toString() : null);
//			map.put("firstName", response[3] !=null ? response[3].toString() : null);
//			map.put("lastName", response[4] !=null ? response[4].toString() : null);
//			map.put("mobileNumber", response[5] !=null ? response[5].toString() : null);
//			map.put("confirmPassword", response[6] !=null ? response[6].toString() : null);
//			map.put("createdAt", response[7] !=null ? response[7].toString() : null);
//			map.put("modifiedAt",response[8] !=null ? response[8].toString() : null);
//			map.put("isActive", response[9] !=null ? response[9].toString() : null);
//			finalresp.add(map);
//		}
//		session.close();
         List<UserModel> resp = new ArrayList<>();
         List<UserModel> resp1 = new ArrayList<>();
         while(rs.next()) {
        	 UserModel user = new UserModel();
        	 UserModel user1 = new UserModel();
        	 user.set_id((Integer) rs.getObject(1));
        	 user.setFirstName((String) rs.getObject(4));
        	 user.setLastName((String) rs.getObject(5));
        	 user1.set_id((Integer) rs.getObject(1));
        	 user1.setFirstName((String) rs.getObject(4));
        	 user1.setLastName((String) rs.getObject(5));
//        	 user.setCreatedAt((Timestamp) rs.getObject(8));
        	 resp.add(user);
        	 resp1.add(user1);
         }
         for(UserModel user :resp) {
        	 user.setEmail("minimum");
        	 finalresp.add(user);
         }
         for(UserModel user :resp1) {
        	 user.setEmail("maximum");
        	 finalresp.add(user);
         }
		}catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 5. Clean up the environment
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
		return finalresp;
	}


	public String verify(UserModel user) {
		Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
		 
		if(authentication.isAuthenticated()) {
			return jwt.generateToken(user.getEmail());
		}
		
		return null;
	}
	
	public UserModel getUserByUserEmail(String username) {
        return userRepo.findByEmail(username);
    }

}
