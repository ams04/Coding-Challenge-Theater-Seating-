package com.theaterseating.service;

import java.util.List;

import com.theaterseating.model.TheaterLayout;
import com.theaterseating.model.TheaterRequest;

/**
 * @author Akshay
 * 
 * Interface for implementing the Theater Seating logic.
 *
 */
public interface TheaterSeatingService {
    
    void processTicketRequests(TheaterLayout layout, List<TheaterRequest> requests);

}
