package com.theaterseating.main;

import java.util.List;
import java.util.Scanner;

import com.theaterseating.model.TheaterLayout;
import com.theaterseating.model.TheaterRequest;
import com.theaterseating.repository.TheatreAndTicketDataProcessAndValidation;
import com.theaterseating.repository.TheatreAndTicketDataProcessAndValidationImpl;
import com.theaterseating.service.TheaterSeatingService;
import com.theaterseating.service.TheaterSeatingServiceImpl;

public class Main {
  
	public static void main(String[] args) {

		TheaterSeatingService service = new TheaterSeatingServiceImpl();

		TheatreAndTicketDataProcessAndValidation repository = new TheatreAndTicketDataProcessAndValidationImpl();
		
		String input;
		StringBuilder layout = new StringBuilder();
		StringBuilder ticketRequests = new StringBuilder();
		boolean isLayoutFinished = false;

		System.out.println("Please enter the Theater Layout and Ticket requests. Then type 'done' on a new line and press enter.\n ");

		Scanner src = new Scanner(System.in);

		while ((input = src.nextLine()) != null && !input.equals("done")) {

			if (input.length() == 0) {

				isLayoutFinished = true;
				continue;

			}

			else if (!isLayoutFinished) {

				layout.append(input + System.lineSeparator());

			} else {

				ticketRequests.append(input + System.lineSeparator());

			}

		}

		src.close();

		try {

			TheaterLayout theaterLayout = repository.getTheaterLayout(layout.toString());

			List<TheaterRequest> requests = repository.getTicketRequests(ticketRequests.toString());

			service.processTicketRequests(theaterLayout, requests);

		} catch (NumberFormatException e) {

			System.out.println(e.getMessage());

		} catch (Exception e) {

			e.printStackTrace();

		}

	}

}
