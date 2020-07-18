package com.hfarhani.gateway.controllers;

import com.hfarhani.gateway.config.JwtTokenUtil;
import com.hfarhani.gateway.dto.JwtRequest;
import com.hfarhani.gateway.dto.JwtResponse;
import com.hfarhani.gateway.dto.UserDto;
import com.hfarhani.gateway.enums.ErrorEnum;
import com.hfarhani.gateway.service.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<Object> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws AuthenticationException {


        try {
            Authentication authentication = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final UserDetails userDetails = (UserDto) authentication.getPrincipal();
            final String token = jwtTokenUtil.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername()));
        } catch (UsernameNotFoundException usernameNotFoundException) {
            return ResponseEntity.ok(ErrorEnum.USER_NOT_FOUND);
        } catch (DisabledException e) {
            return ResponseEntity.ok(ErrorEnum.USER_NOT_FOUND);

        } catch (BadCredentialsException e) {
            return ResponseEntity.ok(ErrorEnum.BAD_CREDENTIALS);
        }

    }

    private Authentication authenticate(String username, String password) throws AuthenticationException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

            return authentication;
        } catch (DisabledException e) {
            throw e;
        } catch (BadCredentialsException e) {
            throw e;
        }
    }
}
