package com.concrete.realtor.events.respositories;

import com.concrete.realtor.events.models.EventBean;

import java.util.List;

public interface  EventRepo {


    List<EventBean> getAllEvents();

    void addEvents(EventBean addEventRequest);

    void updateEvents(int id, EventBean updateEventRequest);
}
