package com.hfarhani.gateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JwtRequest {

    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;

}
