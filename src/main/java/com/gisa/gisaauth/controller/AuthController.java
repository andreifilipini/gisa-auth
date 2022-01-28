package com.gisa.gisaauth.controller;

import com.gisa.gisaauth.dto.LoginRequestDTO;
import com.gisa.gisaauth.dto.LoginResponseDTO;
import com.gisa.gisaauth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private AuthService authService;

	@PostMapping
	public ResponseEntity<LoginResponseDTO> login(@QueryParam("login") String login, @QueryParam("password") String password) {
		try {
			return ResponseEntity.ok(authService.authenticate(new LoginRequestDTO(login, password)));
		} catch (NotAuthorizedException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
