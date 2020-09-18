package com.carpark.model;

public class Car implements Vehicle {
	private String vehiclePlate;
	public Car(String vehiclePlate) {
		this.vehiclePlate = vehiclePlate;
	}
	@Override
	public String getVehiclePlate() {
		return vehiclePlate;
	}

}
