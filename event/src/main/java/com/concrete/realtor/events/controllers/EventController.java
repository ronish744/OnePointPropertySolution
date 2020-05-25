package com.concrete.realtor.events.controllers;

import com.concrete.realtor.events.models.EventBean;
import com.concrete.realtor.events.models.Result;
import com.concrete.realtor.events.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/events",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
public class EventController {

    @Autowired
    private EventService eventService;


    @PostMapping
    public ResponseEntity<Result<String>> addEvent(@Valid @RequestBody EventBean addEventRequest ){
        eventService.addEvents(addEventRequest);
        Result<String> result = new Result<>(HttpStatus.CREATED.value(), "success","");
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

    @GetMapping("/")
    public List<EventBean> getAllEvent() {
        return  eventService.getAllEvents();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result<String>> updateEvent(@PathVariable("id") int id,@Valid @RequestBody EventBean eventBean){
        eventService.updateEvents(id,eventBean);
        Result<String> result = new Result<>(HttpStatus.OK.value(), "success","");
        return new ResponseEntity<>(result, HttpStatus.valueOf(result.getCode()));
    }

}
