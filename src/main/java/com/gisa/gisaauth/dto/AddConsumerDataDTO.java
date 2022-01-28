package com.gisa.gisaauth.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class AddConsumerDataDTO implements Serializable {

    private static final long serialVersionUID = -4414221790148060623L;

    private Integer created_at;
    private String id;
    private String algorithm;
    private String secret;
    private String key;

}