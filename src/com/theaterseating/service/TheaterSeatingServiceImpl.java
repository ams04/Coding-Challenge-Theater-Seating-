package com.theaterseating.service;

import java.util.List;

import com.theaterseating.model.TheaterLayout;
import com.theaterseating.model.TheaterRequest;
import com.theaterseating.model.TheaterSection;

/**
 * @author Akshay
 * 
 * Implementation class for defining the algorithm for seat selection.
 *
 */
public class TheaterSeatingServiceImpl implements TheaterSeatingService {

	private boolean checker = false;
	
	 /**
     * @param layout the {@link TheaterLayout} entered by the user.
     * @param requests the {@link TheaterRequest}s entered by the user.
     * 
     * Note: For the purpose of this solution and printing the results to the console, below legends are used.
     * 		1. Value of "-1" in the section number indicates that the request needs to be "Call to split party".
     * 		2. Value of "-2" in the section number indicate that "We can't handle the party".
     * 		3. Value of anything other than these 2 indicate the section and row number of that request. The section and row number
     * 			are updated in the {@link TheaterRequest} object.
     */
    @Override
    public void processTicketRequests(TheaterLayout layout, List<TheaterRequest> requests) {
        
    	/**
    	 * Iterating through all the entered requests.
    	 */
		for (int i = 0; i < requests.size(); i++) {

			TheaterRequest request = requests.get(i);
			if (request.isRequestCompleted())
				continue;
            
            /**
             * Checks if the number of requested tickets in the current iteration is greater than the total number of seats available in the theater. 
             * If so, then "-2" will be set to the current {@link TheaterRequest} object's row and section fields.
             */
			if (request.getNoOfTickets() > layout.getAvailableSeats()) {

				request.setRowNumber(-2);
				request.setSectionNumber(-2);
				continue;

			}

			List<TheaterSection> sections = layout.getSections();
            
            /**
             * Iterating through the {@link TheaterSection}s for every {@link TheaterRequest}. 
             * 
             * Below logic is followed for finding the right seat and section number:
             * 			Step 1: If there is an exact match in the current {@link TheaterRequest}'s number of seats and the current {@link TheaterSection} object,
             * 					then the seat and row number are set according to the current {@link TheaterSection} object.
             * 			Step 2: If the number of seats in the current section is greater than the required number of seats, the algorithm finds a complement
             * 					{@link TheaterRequest}.
             * 					a. IF a complement is found (Refer (TheaterSeatingServiceImpl.java:238))
             * 							set both {@link TheaterRequest}s to the current {@link TheaterSection}.
             * 					b. ELSE
             * 							the {@ TheaterRequest} is set to the first occurring section that has the same number of available seats.
             * 			Step 3: After the above algorithm is carried out, an edge case is handled at line (TheaterSeatingServiceImpl.java:165).
             * 					For Example: If there is a section which has seats left over after assigning a {@link TheaterRequest}. The list of requests 
             * 								 are checked if there is an exact number of seats required for any request. If there is a match, the newly found 
             * 								 {@link TheaterRequest} is replaced in the current section. 
             * 					
             */			
			for (int j = 0; j < sections.size(); j++) {

				TheaterSection section = sections.get(j);
                
                /**
                 * Step 1.
                 */
				if (request.getNoOfTickets() == section.getAvailableSeats()) {

					request.setRowNumber(section.getRowNumber());
					request.setSectionNumber(section.getSectionNumber());
					request.setTs(section);
					section.setAvailableSeats(section.getAvailableSeats() - request.getNoOfTickets());
					layout.setAvailableSeats(layout.getAvailableSeats() - request.getNoOfTickets());
					request.setRequestCompleted(true);
					break;

				} else if (request.getNoOfTickets() < section.getAvailableSeats()) {

					int requestNo = findComplementRequest(requests,
							section.getAvailableSeats() - request.getNoOfTickets(), i);

					if (requestNo != -1) {

						request.setRowNumber(section.getRowNumber());
						request.setSectionNumber(section.getSectionNumber());
						request.setTs(section);
						section.setAvailableSeats(section.getAvailableSeats() - request.getNoOfTickets());
						layout.setAvailableSeats(layout.getAvailableSeats() - request.getNoOfTickets());
						request.setRequestCompleted(true);

						TheaterRequest complementRequest = requests.get(requestNo);

						complementRequest.setRowNumber(section.getRowNumber());
						complementRequest.setSectionNumber(section.getSectionNumber());
						complementRequest.setTs(section);
						section.setAvailableSeats(section.getAvailableSeats() - complementRequest.getNoOfTickets());
						layout.setAvailableSeats(layout.getAvailableSeats() - complementRequest.getNoOfTickets());
						complementRequest.setRequestCompleted(true);

						section.setSectionShared(true);

						break;

					}
                    /**
                     * Step 2.
                     */
					else {

						TheaterSection exactMatch = findExactMatch(request.getNoOfTickets(), sections);

						if (exactMatch != null) {

							request.setRowNumber(exactMatch.getRowNumber());
							request.setSectionNumber(exactMatch.getSectionNumber());
							request.setTs(exactMatch);
							exactMatch.setAvailableSeats(exactMatch.getAvailableSeats() - request.getNoOfTickets());
							layout.setAvailableSeats(layout.getAvailableSeats() - request.getNoOfTickets());
							request.setRequestCompleted(true);
							break;
						}

						else {
							request.setRowNumber(section.getRowNumber());
							request.setSectionNumber(section.getSectionNumber());
							request.setTs(section);
							section.setAvailableSeats(section.getAvailableSeats() - request.getNoOfTickets());
							layout.setAvailableSeats(layout.getAvailableSeats() - request.getNoOfTickets());
							request.setRequestCompleted(true);
							break;
						}

					}
                    
                }
                
            }
            
			if (!request.isRequestCompleted()) {

				request.setRowNumber(-1);
				request.setSectionNumber(-1);

			}

		}
        
        /**
         * Step 3
         */
		for (TheaterRequest request : requests) {

			checker = false;
			if (request.getTs() != null && request.getTs().getAvailableSeats() != 0) {

				List<TheaterSection> sections = layout.getSections();
				for (TheaterSection section : sections) {

					if (section.getCapacity() == request.getTs().getCapacity()) {
						if (section.isSectionShared()) {
							continue;
						} else {

							for (TheaterRequest innerRequest : requests) {
								if (innerRequest.getNoOfTickets() == section.getCapacity()) {

									TheaterRequest temp;
									TheaterSection exactCapacity = findExactMatch(request.getNoOfTickets(), sections);
									if (exactCapacity == null) {
										temp = new TheaterRequest();
										temp.setName(request.getName());
										temp.setNoOfTickets(request.getNoOfTickets());
										temp.setRowNumber(request.getRowNumber());
										temp.setSectionNumber(request.getSectionNumber());
										temp.setTs(request.getTs());
									}

									else {
										temp = new TheaterRequest();
										temp.setName(request.getName());
										temp.setNoOfTickets(request.getNoOfTickets());
										temp.setRowNumber(exactCapacity.getRowNumber());
										temp.setSectionNumber(exactCapacity.getSectionNumber());
									}
									request.setRowNumber(innerRequest.getRowNumber());
									request.setSectionNumber(innerRequest.getSectionNumber());
									request.getTs().setAvailableSeats(
											request.getTs().getCapacity() - request.getNoOfTickets());

									innerRequest.setRowNumber(temp.getRowNumber());
									innerRequest.setSectionNumber(temp.getSectionNumber());
									innerRequest.getTs().setAvailableSeats(
											innerRequest.getTs().getCapacity() - innerRequest.getNoOfTickets());

									checker = true;
									break;

								}
							}
						}
					}
					if (checker)
						break;
				}
			}

		}
		
		/**
		 * Print the processed request.
		 */
		System.out.println("Seats Distribution.\n");

		for (TheaterRequest request1 : requests) {

			System.out.println(request1.getStatus());

		}

	}
    /**
     * This method finds a seat request which could complement the number of seats for a given section
     * 
     * Example: Section capacity = 7
     * 			Current seat request = 4 seats
     * 			Remaining seats in section = 3
     * 
     * This method finds if there is any request with number of seats required = 3.
     * 
     * @param requests the {@link TheaterRequest}s entered by the user.
     * @param complementSeats the number of seats that could complements the number of seats for the current request.
     * @param currentRequestIndex the index of the {@link TheaterRequest} in the list of {@link TheaterRequest} object.
     * 
     * @return the index of the complement {@link TheaterRequest} .
     */
	private int findComplementRequest(List<TheaterRequest> requests, int complementSeats, int currentRequestIndex) {

		int requestNo = -1;

		for (int i = currentRequestIndex + 1; i < requests.size(); i++) {

			TheaterRequest request = requests.get(i);

			if (!request.isRequestCompleted() && request.getNoOfTickets() == complementSeats) {

				requestNo = i;
				break;

			}

		}

		return requestNo;
	}

	/**
	 * Helper method used the find the {@link TheaterSection} with the exact number of seats are requested.
	 * 
	 * @param noOfTickets the number of requested seats.
	 * @param sections the input sections.
	 * 
	 * @return the {@link TheaterSection} with the exact number of seats as required.
	 */
	private TheaterSection findExactMatch(int noOfTickets, List<TheaterSection> sections) {

		for (TheaterSection section : sections) {
			if (section.getAvailableSeats() == noOfTickets) {
				return section;
			}
		}

		return null;
	}
}