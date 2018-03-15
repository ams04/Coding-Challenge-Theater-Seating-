package com.theaterseating.model;

import java.util.List;

/**
 * @author Akshay.
 * 
 * This pojo represents the layout entered by the user. A {@link TheaterLayout} object contains a list of {@link TheaterSection}s.
 *
 */
public class TheaterLayout {

    private int totalCapacity;
    private int availableSeats;
    private List<TheaterSection> sections;

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public List<TheaterSection> getSections() {
        return sections;
    }

    public void setSections(List<TheaterSection> sections) {
        this.sections = sections;
    }
    
}
