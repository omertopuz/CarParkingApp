package com.carpark.model;

public class SlotBusyState implements SlotState {

	@Override
	public boolean getAvailablity() {
		return false;
	}

}
