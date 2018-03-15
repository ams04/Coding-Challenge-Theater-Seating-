package com.theaterseating.model;

/**
 * @author Akshay
 *
 */
public class TheaterRequest {

	private TheaterSection ts;
    private String nameOfCustomer;
    private int noOfTickets;
    private boolean isRequestCompleted;
    private int rowNumber;
    private int sectionNumber;
   
	public TheaterSection getTs() {
		return ts;
	}

	public void setTs(TheaterSection ts) {
		this.ts = ts;
	}
	
    public String getName() {
        return nameOfCustomer;
    }

    public void setName(String name) {
        this.nameOfCustomer = name;
    }

    public int getNoOfTickets() {
        return noOfTickets;
    }

    public void setNoOfTickets(int noOfTickets) {
        this.noOfTickets = noOfTickets;
    }

    public boolean isRequestCompleted() {
        return isRequestCompleted;
    }

    public void setRequestCompleted(boolean isCompleted) {
        this.isRequestCompleted = isCompleted;
    }
    
    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getSectionNumber() {
        return sectionNumber;
    }

    public void setSectionNumber(int sectionNumber) {
        this.sectionNumber = sectionNumber;
    }
    
    public String getStatus(){
        
        String status = null;
        
        if(isRequestCompleted){
            
            status = nameOfCustomer + " " + "Row " + rowNumber + " " + "Section " + sectionNumber;
            
        }else{
            
            if(rowNumber == -1 && sectionNumber == -1){
                
                status = nameOfCustomer + " " + "Call to split party.";
                
            }else{
                
                status = nameOfCustomer + " " + "Sorry, we can't handle your party.";
                
            }
            
        }
        
        return status;
    }


}
