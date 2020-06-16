package com.onepointpropertybackend.events.respositories;

import com.onepointpropertybackend.events.models.EventBean;

import java.util.List;

public interface  EventRepo {


    List<EventBean> getAllEvents();

    void addEvents(EventBean addEventRequest);

    void updateEvents(int id, EventBean updateEventRequest);
}
