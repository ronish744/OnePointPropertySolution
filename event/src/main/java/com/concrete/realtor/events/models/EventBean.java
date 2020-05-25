package com.concrete.realtor.events.models;


import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class EventBean {

    @NotEmpty(message = "please enter eventname")
    private String eventName;
    @NotEmpty(message = "please enter eventtype")
    private String eventType;
    @NotEmpty(message = "please enter eventaddress")
    private String eventAddress;
    private int projectId;
    @NotNull(message = "please enter eventdate")
    private Date eventDate;
    @NotEmpty(message = "please enter eventDescription")
    private String eventDescription;
    private String eventImage;
}
