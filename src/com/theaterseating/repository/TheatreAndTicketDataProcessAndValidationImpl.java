package com.theaterseating.repository;

import java.util.ArrayList;
import java.util.List;

import com.theaterseating.model.TheaterLayout;
import com.theaterseating.model.TheaterRequest;
import com.theaterseating.model.TheaterSection;

public class TheatreAndTicketDataProcessAndValidationImpl implements TheatreAndTicketDataProcessAndValidation{

/**
 * This function validates the input entered by the user and returns a {@link TheaterLayout} object, with all the fields initialized.
 * 
 * @param inputLayout the layout entered by the user.
 * 
 * return a {@link TheaterLayout} object.
 */
    @Override
    public TheaterLayout getTheaterLayout(String inputLayout) throws NumberFormatException{
        
        TheaterLayout theaterLayout = new TheaterLayout();
        TheaterSection section;
        List<TheaterSection> sectionsList = new ArrayList<TheaterSection>();
        int totalCapacity = 0, value;
        String[] rows = inputLayout.split(System.lineSeparator());
        String[] sections;
        
        for(int i=0 ; i<rows.length ; i++){
            
            sections = rows[i].split(" ");
            
            for(int j=0 ; j<sections.length ; j++){
            
                try{
                
                    value = Integer.valueOf(sections[j]);
                    
                }catch(NumberFormatException nfe){
                    
                    throw new NumberFormatException("'" + sections[j] + "'" + " is an invalid section capacity");
                    
                }
                
                totalCapacity = totalCapacity + value;
                
                section = new TheaterSection();
                section.setRowNumber(i + 1);
                section.setSectionNumber(j + 1);
                section.setCapacity(value);
                section.setAvailableSeats(value);
                
                sectionsList.add(section);
                
            }

        }
        
        theaterLayout.setTotalCapacity(totalCapacity);
        theaterLayout.setAvailableSeats(totalCapacity);
        theaterLayout.setSections(sectionsList);
        
        return theaterLayout;
        
    }

    /**
     * This function validates the user's input and returns a list of {@link TheaterRequest} objects by processing the input.
     * 
     * @param the ticket requests entered in a String format.
     * 
     * return a List of {@link TheaterRequest}.
     */
    @Override
    public List<TheaterRequest> getTicketRequests(String ticketRequests) throws NumberFormatException{
        
        List<TheaterRequest> requestsList = new ArrayList<TheaterRequest>();
        TheaterRequest request;
        
        String[] requests = ticketRequests.split(System.lineSeparator());
        
        for(String r : requests){
            
            String[] rData = r.split(" ");
            
            request = new TheaterRequest();
            
            request.setName(rData[0]);
            
            try{
            
                request.setNoOfTickets(Integer.valueOf(rData[1]));
                
            }catch(NumberFormatException nfe){
                
                throw new NumberFormatException("'" + rData[1] + "'" + " is invalid ticket request.");
            }
            request.setRequestCompleted(false);
            
            requestsList.add(request);
            
        }
        
        return requestsList;
        
    }
}
