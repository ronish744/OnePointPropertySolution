package com.onepointpropertybackend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	
	@NotEmpty(message = "userName is required field")
	private String userName;
	
	@NotEmpty(message = "password is required field")
	private String password;
}
