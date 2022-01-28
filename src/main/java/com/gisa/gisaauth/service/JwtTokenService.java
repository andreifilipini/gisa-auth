package com.gisa.gisaauth.service;

import com.gisa.gisaauth.model.Role;
import com.gisa.gisaauth.model.RoleEnum;
import com.gisa.gisaauth.model.User;
import com.gisa.gisacore.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class JwtTokenService {

	public static final String CLAIM_ROLES = "roles";
	public static final String CLAIM_ISS = "iss";

	@Value("${jwt.secret}")
    private String secret;

	@Value("${jwt.expiresIn}")
	private Integer expiresIn;

	private JwtTokenUtil jwtTokenUtil;

	@PostConstruct
	private void init() {
		this.jwtTokenUtil = new JwtTokenUtil(secret, expiresIn);
	}

	public String generateWithIss(User user, String iss) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(CLAIM_ROLES, user.getRoles().stream()
				.map(Role::getName)
				.map(RoleEnum::name)
				.collect(Collectors.toList()));
		claims.put(CLAIM_ISS, iss);
		return jwtTokenUtil.generate(claims, user.getLogin());
	}

}