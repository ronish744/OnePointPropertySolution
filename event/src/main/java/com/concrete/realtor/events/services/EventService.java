package com.concrete.realtor.events.services;

import com.concrete.realtor.events.models.EventBean;
import com.concrete.realtor.events.respositories.EventRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class EventService {

    @Autowired
    @Qualifier("EventRepo")
    private  EventRepo eventRepo;


    public List<EventBean> getAllEvents()  {
        List<EventBean> list = eventRepo.getAllEvents();
        if (!list.isEmpty())
            return list;
        return  null;
    }

    public void addEvents(EventBean addEventRequest) {
        eventRepo.addEvents(addEventRequest);
    }

    public void updateEvents(int id, EventBean updateEventRequest) {
        eventRepo.updateEvents(id,updateEventRequest);
    }
}
