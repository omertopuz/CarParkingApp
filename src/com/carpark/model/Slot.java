package com.carpark.model;


import java.util.UUID;

public class Slot implements SlotState {

	private String letterCoordinate;
	private String numberCoordinate;
	private int distanceFromGate;
	private SlotState currentState;
	private UUID currentTicket;
	
	public Slot(String letterCoordinate, String numberCoordinate,int distanceFromGate) {
		this.letterCoordinate = letterCoordinate;
		this.numberCoordinate = numberCoordinate;
		this.distanceFromGate = distanceFromGate;
		currentState = new SlotAvailableState();
	}

	protected Slot(String letterCoordinate, String numberCoordinate) {
		this(letterCoordinate, numberCoordinate,0);
	}

	public String getCustomAdress() {
		return "Letter:" + this.letterCoordinate + " - Number : " + this.numberCoordinate;
	}	
	
	public SlotState getCurrentState() {
		return currentState;
	}

	public void setCurrentState(SlotState currentState) {
		this.currentState = currentState;
	}

	public int getDistanceFromGate() {
		return distanceFromGate;
	}

	public UUID getCurrentTicket() {
		return currentTicket;
	}

	public void setCurrentTicket(UUID currentTicket) {
		this.currentTicket = currentTicket;
	}

	@Override
	public boolean equals(Object obj) {
		Slot slot = (Slot) obj;
		return slot.getCustomAdress().equals(this.getCustomAdress());
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return getCustomAdress()+"\tDistance : "+ distanceFromGate+"\t" + (currentState.getAvailablity() ? "Vacant":"Busy");
	}
	
	@Override
	public boolean getAvailablity() {
		return this.currentState.getAvailablity();
	}
}
