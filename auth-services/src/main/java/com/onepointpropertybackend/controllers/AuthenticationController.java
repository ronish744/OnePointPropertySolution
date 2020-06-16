package com.onepointpropertybackend.controllers;

import com.onepointpropertybackend.models.LoginRequest;
import com.onepointpropertybackend.models.LoginResponse;
import com.onepointpropertybackend.services.MyUserDetailServices;
import com.onepointpropertybackend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailServices userDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping
	public String helloWorld() {
		return "Test Hello String";
	}

	@PostMapping("/authenticate")
	public ResponseEntity<LoginResponse> createAuthenticationToken(@Valid @RequestBody LoginRequest loginReq)
			throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginReq.getUserName(), loginReq.getPassword()));
		} catch (BadCredentialsException ex) {
			throw new Exception("Invalid User Name or Password.", ex);
		}

		UserDetails userDetails = userDetailsService.loadUserByUsername(loginReq.getUserName());
		String jwt = jwtUtil.generateToken(userDetails);
		String roles = userDetails.getAuthorities().stream().map(authorities -> authorities.getAuthority())
				.collect(Collectors.joining(","));
		return ResponseEntity.ok(new LoginResponse(jwt, loginReq.getUserName(), roles));
	}
}
