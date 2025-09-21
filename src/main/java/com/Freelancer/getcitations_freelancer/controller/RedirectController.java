package com.Freelancer.getcitations_freelancer.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Freelancer.getcitations_freelancer.Repository.LinkClickRepository;
import com.Freelancer.getcitations_freelancer.model.LinkClick;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/public/r")
public class RedirectController {

 private final LinkClickRepository repo;

 // Map slugs to real URLs (could be stored in DB or config)
 private final Map<String, String> slugToUrl = Map.of(
     "resume", "https://get-citationsv2.vercel.app/",
     "portfolio", "https://get-citationsv2.vercel.app/",
     "naukri", "https://get-citationsv2.vercel.app/"
 );

 public RedirectController(LinkClickRepository repo) {
     this.repo = repo;
 }

 @GetMapping("/{slug}")
 public ResponseEntity<Void> redirect(@PathVariable String slug, HttpServletRequest request) {
     String target = slugToUrl.get(slug);
     if (target == null) return ResponseEntity.notFound().build();

     // collect client info
     String ip = extractClientIp(request);
     String ua = request.getHeader("User-Agent");
     String referer = request.getHeader("Referer");

     // save click
     LinkClick click = new LinkClick();
     click.setSlug(slug);
     click.setIp(ip);
     click.setUserAgent(ua);
     click.setReferer(referer);
     repo.save(click);

     // redirect
     HttpHeaders headers = new HttpHeaders();
     headers.setLocation(URI.create(target));
     return ResponseEntity.status(302).headers(headers).build();
 }

 private String extractClientIp(HttpServletRequest request) {
     String xf = request.getHeader("X-Forwarded-For");
     if (xf != null && !xf.isBlank()) return xf.split(",")[0].trim();
     return request.getRemoteAddr();
 }
}

