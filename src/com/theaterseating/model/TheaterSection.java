package com.theaterseating.model;

/**
 * @author Akshay
 * 
 * This pojo represents a section inside a row, inside a Theater.
 *
 */
public class TheaterSection {

	private boolean isSectionShared;
	private int rowNumber;
	private int sectionNumber;
	private int capacity;
	private int availableSeats;

	public boolean isSectionShared() {
		return isSectionShared;
	}

	public void setSectionShared(boolean isSectionShared) {
		this.isSectionShared = isSectionShared;
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

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

}
