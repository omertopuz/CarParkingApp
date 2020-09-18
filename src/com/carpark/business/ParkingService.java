package com.carpark.business;

import java.util.List;
import java.util.Optional;

import com.carpark.model.FindVacantSlotStrategy;
import com.carpark.model.ParkingTicket;
import com.carpark.model.Slot;
import com.carpark.model.Vehicle;

public interface ParkingService {
	Optional<Slot> findSlotByCar(Vehicle car);
	Optional<Vehicle> findCarBySlot(Slot slot);

	Slot parkCar(Vehicle car,FindVacantSlotStrategy slot);
	
	
	ParkingTicket freeSlot(Slot slot);
	void addSlot(Slot slot);
	List<Slot> findAllVacantSlots();
}
