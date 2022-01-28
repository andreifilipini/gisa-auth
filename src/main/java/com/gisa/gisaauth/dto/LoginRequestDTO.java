package com.gisa.gisaauth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class LoginRequestDTO implements Serializable {

    private final String login;
    private final String password;
}
