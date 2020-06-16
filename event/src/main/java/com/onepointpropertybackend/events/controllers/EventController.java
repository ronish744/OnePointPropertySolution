package com.onepointpropertybackend.events.controllers;

import com.onepointpropertybackend.events.email.EmailService;
import com.onepointpropertybackend.events.models.EventBean;
import com.onepointpropertybackend.events.models.Result;
import com.onepointpropertybackend.events.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/events")
public class EventController {

	@Autowired
	private EventService eventService;

	@Autowired
	@Qualifier("eventEmailService")
	private EmailService emailService;

	@GetMapping("/hello")
	public String helloworld() {
		return "hello";
	}

	@PostMapping
	public ResponseEntity<Result<String>> addEvent(@Valid @RequestBody EventBean addEventRequest) {
		eventService.addEvents(addEventRequest);
		Result<String> result = new Result<>(HttpStatus.CREATED.value(), "success", "");
		return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
	}

	@PostMapping(value = "/send-event-details", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public String uploadFile(@RequestParam(name = "eventFile") MultipartFile file,
							 @RequestParam(name = "emailList") String mailIdArray) {
		Arrays.stream(mailIdArray.split(","))
				.forEach(emailId -> emailService.sendMailWithAttachment(emailId, "Subject Line", "Mail Body", file));
		return file.getOriginalFilename();
	}

	@GetMapping
	public List<EventBean> getAllEvent() {
		return eventService.getAllEvents();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Result<String>> updateEvent(@PathVariable("id") int id,
			@Valid @RequestBody EventBean eventBean) {
		eventService.updateEvents(id, eventBean);
		Result<String> result = new Result<>(HttpStatus.OK.value(), "success", "");
		return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
	}

}
