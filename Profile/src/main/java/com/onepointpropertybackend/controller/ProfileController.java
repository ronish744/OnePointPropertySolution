package com.onepointpropertybackend.controller;


import com.onepointpropertybackend.model.ForgotPasswordReq;
import com.onepointpropertybackend.model.Profile;
import com.onepointpropertybackend.model.Result;
import com.onepointpropertybackend.service.EmailService;
import com.onepointpropertybackend.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping(path = "/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileController {

	@Autowired
	private EmailService emailService;

	@Autowired
    ProfileService profileService;

	@GetMapping("/")
	public List<Profile> getAllProfile() {
		return profileService.findAllProfiles();
	}

	@GetMapping("/{emailid}")
	public Profile getProfileById(@PathVariable("emailid") String emailid ) {
		return profileService.findProfile(emailid);
	}

	@PostMapping
	public ResponseEntity<Result<String>> addProfile(@Valid @RequestBody Profile addProfileRequest ){
		profileService.addProfile(addProfileRequest);
		int employeeid=profileService.findProfile(addProfileRequest.getEmailid()).getId();
		StringBuilder msgBody=new StringBuilder("Welcome to Concrete Realtors World");
		msgBody.append("\n").append("Below are you details for login into the system").append("\n\n")
				.append("Username : ").append(addProfileRequest.getEmailid()).append("\n")
				.append("Password : ").append(addProfileRequest.getPassword()).append("\n")
				.append("Employee-Id : ").append(employeeid).append("\n");
		emailService.sendMail(addProfileRequest.getEmailid(), "Your profile has been  created successfully!!! ",  msgBody.toString());
		Result<String> result = new Result<>(HttpStatus.CREATED.value(), "success","");
		return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Result<String>> updateEvent(@PathVariable("id") int id,@Valid @RequestBody Profile updateProfile){
		profileService.updateProfile(id,updateProfile);
		///emailService.sendMail(updateProfile.getEmailid(), "Your profile has been update successfully!!!  ","Please visit your profile to check your latest update");
		Result<String> result = new Result<>(HttpStatus.OK.value(), "success","");
		return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
	}
	@PutMapping("/forgot-password")
	public ResponseEntity<Result<String>> forgotPassword(@Valid @RequestBody ForgotPasswordReq forgotPasswordReq){
		profileService.forgotPassword(forgotPasswordReq.getEmailId());

		Profile profileDetails = profileService.findProfile(forgotPasswordReq.getEmailId());

		StringBuilder msgBody=new StringBuilder("Your Password has been successfully updated !!!");
		msgBody.append("\n").append("Below are you details for login into the system").append("\n\n")
				.append("Username : ").append(profileDetails.getEmailid()).append("\n")
				.append("Password : ").append(profileDetails.getPassword()).append("\n")
				.append("Employee-Id : ").append(profileDetails.getId()).append("\n");
		emailService.sendMail(forgotPasswordReq.getEmailId(), "Your Password has been Successfully Updated!!! ",  msgBody.toString());
		Result<String> result = new Result<>(HttpStatus.OK.value(), "success","");
		return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
	}

}

