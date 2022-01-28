package com.gisa.gisaauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class LoginResponseDTO implements Serializable {

    private int expiresIn;
    private String token;
}
