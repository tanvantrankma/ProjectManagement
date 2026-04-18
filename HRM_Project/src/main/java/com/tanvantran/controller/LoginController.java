package com.tanvantran.controller;

import java.security.Principal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/login")
@CrossOrigin("*")
public class LoginController {
	
	@GetMapping
	public ResponseEntity<?> checkLogin(Principal principal){
		// Trả về thông tin của username
		System.out.println("Principal" + principal);
		System.out.println("Username: " + principal.getName());
		return new ResponseEntity<>("Login thành công !", HttpStatus.OK);
	}
}
