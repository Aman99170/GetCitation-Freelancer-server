package com.Freelancer.getcitations_freelancer.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Freelancer.getcitations_freelancer.service.JWTService;
import com.Freelancer.getcitations_freelancer.service.MyUserDetailsService;
import com.Freelancer.getcitations_freelancer.util.JSONParserUtil;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter{

	@Autowired
	private JWTService jwtService;
	@Autowired
	ApplicationContext context;
	@Value("${frontend.url}")
	private String frontendUrl;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		
//		I am making this filter chain as per my authorization coming from frontend. From frontend its coming like {"authToken":"gjdvjav.."}
		System.out.println(request.getHeader("Authorization"));
		String authHeaderObj = request.getHeader("Authorization");
		System.out.println(authHeaderObj);
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
			System.out.println("Error in parsing");
		}
		String userEmail=null;
		if(token!=null && token!="") {
			try {
			userEmail = jwtService.getUserEmailFromToken(token);
			}catch(Exception e) {
				response.setHeader("Access-Control-Allow-Origin", frontendUrl);
			}
		}
		
		if(userEmail!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
			
			UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(userEmail);
			
			if(jwtService.validateToken(token,userDetails)) {
				UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		}
//		response.setHeader("Access-Control-Allow-Origin", frontendUrl);
//        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS,PATCH");
//        response.setHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
//        response.setHeader("Access-Control-Allow-Credentials", "true");

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else {
        	filterChain.doFilter(request, response);
        }
	}

}
