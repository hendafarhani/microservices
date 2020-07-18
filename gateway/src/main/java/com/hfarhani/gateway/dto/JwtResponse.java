package com.hfarhani.gateway.dto;


import lombok.Getter;

@Getter
public class JwtResponse {
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final String username;

    public JwtResponse(String jwttoken, String username) {
        this.jwttoken = jwttoken;
        this.username = username;
    }
}
