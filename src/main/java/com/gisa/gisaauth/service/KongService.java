package com.gisa.gisaauth.service;

import com.gisa.gisaauth.dto.AddConsumerDataDTO;
import com.gisa.gisacore.exception.InfraException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class KongService {

    public static final String CLAIM_ROLES = "roles";
    public static final String CONSUMERS_URL = "%s/consumers";
    public static final String CONSUMERS_JWT_URL = "%s/consumers/%s/jwt";


    @Value("${kong.url}")
    private String url;

    @Value("${jwt.secret}")
    private String secret;

    private RestTemplate restTemplate;

    @PostConstruct
    private void init() {
        this.restTemplate = new RestTemplate();
    }

    public String login(String login) {
        register(login);
        ResponseEntity<AddConsumerDataDTO> response = authenticate(login);

        if (HttpStatus.CREATED.equals(response.getStatusCode())) {
            return response.getBody().getKey();
        }
        throw new InfraException("Problema ao registrar Consumer no API Gateway."+response.getStatusCodeValue());
    }

    private ResponseEntity<AddConsumerDataDTO> authenticate(String login) {
        HttpHeaders headers = getHttpHeaders();

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("algorithm", SignatureAlgorithm.HS512.name());
        map.add("secret", this.secret);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        return restTemplate.postForEntity(String.format(CONSUMERS_JWT_URL, this.url, login), request, AddConsumerDataDTO.class);
    }

    private void register(String login) {
        HttpHeaders headers = getHttpHeaders();

        MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
        map.add("username", login);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        try {
            restTemplate.postForEntity(String.format(CONSUMERS_URL, this.url, login), request, null);
        } catch (HttpClientErrorException e) {
            if (!HttpStatus.CONFLICT.equals(e.getStatusCode())) {
                throw e;
            }
        }
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return headers;
    }

}