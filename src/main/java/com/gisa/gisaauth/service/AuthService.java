package com.gisa.gisaauth.service;

import com.gisa.gisaauth.dto.LoginRequestDTO;
import com.gisa.gisaauth.dto.LoginResponseDTO;
import com.gisa.gisaauth.model.User;
import com.gisa.gisaauth.model.repository.UserRepository;
import com.gisa.gisacore.util.CipherUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;

@Service
public class AuthService {

	@Inject
	private UserRepository repository;

	@Inject
	private JwtTokenService jwtTokenService;

	@Inject
	private KongService kongService;

	@Value("${jwt.expiresIn}")
	private Integer expiresIn;

	public LoginResponseDTO authenticate(LoginRequestDTO loginRequestDTO) {

		if(!repository.existsByLoginAndPassword(loginRequestDTO.getLogin(), CipherUtil.encrypt(loginRequestDTO.getPassword()))) {
			throw new NotAuthorizedException("Login e/ou senha inválido.");
		}

		User user = repository.findByLogin(loginRequestDTO.getLogin());

		String key = kongService.login(user.getLogin());

		String jwt = jwtTokenService.generateWithIss(user,  key);

		return new LoginResponseDTO(expiresIn, jwt);
	}
}
