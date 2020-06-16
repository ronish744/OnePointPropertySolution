package com.onepointpropertybackend.controllers;


//import com.onepointpropertybackend.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/core-service")
@Validated
//Test Service is working or not
public class CoreServiceController {

//	@Autowired
//	private EmailService emailService;

	@GetMapping()
	public String helloWorld() {
		return "Hello World, Core Services is working fine!!!";
	}

	// This mapping will send simple mail with message subject and body
//	@GetMapping("/send-mail")
//	public String sendEmail() {
//		emailService.sendMail("gauripatildec6@gmail.com", "Subject Line", "Mail Body");
//		return "Mail sent successfully without any attachment";
//	}
//
	@PostMapping("/error-mapping")
	public String testErrorMappiing(@RequestBody String ddummyValue) {
		return "PsotMapping Successfully Called Witout Error";
	}
}
