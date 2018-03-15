package com.theaterseating.repository;

import java.util.List;

import com.theaterseating.model.TheaterLayout;
import com.theaterseating.model.TheaterRequest;

public interface TheatreAndTicketDataProcessAndValidation {

    TheaterLayout getTheaterLayout(String rawLayout);
    
    List<TheaterRequest> getTicketRequests(String ticketRequests);
    
}
