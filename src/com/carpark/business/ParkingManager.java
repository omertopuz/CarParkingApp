package com.carpark.business;

import java.time.LocalDateTime;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.carpark.model.FindVacantSlotStrategy;
import com.carpark.model.ParkingLot;
import com.carpark.model.ParkingTicket;
import com.carpark.model.Slot;
import com.carpark.model.SlotAvailableState;
import com.carpark.model.SlotBusyState;
import com.carpark.model.Vehicle;

public class ParkingManager implements ParkingService{
	
	private ParkingLot parkingLot;

	
	public ParkingManager(ParkingLot parkingLot) {
		this.parkingLot = parkingLot;
	}
	
	@Override
	public void addSlot(Slot slot) {
		this.parkingLot.getSlotList().put(slot,null);
	}

	@Override
	public Slot parkCar(Vehicle car,FindVacantSlotStrategy vacantSlot) {
		if(vacantSlot == null)
			throw new RuntimeException("Define getting vacant slot strategy!");
		
		Slot parkingSlot=vacantSlot.findVacantSlot(findAllVacantSlots());
		this.parkingLot.getSlotList().replace(parkingSlot, car);
		this.parkingLot.getParkingTicketList().add(getTicket(parkingSlot,car.getVehiclePlate()));

		parkingSlot.setCurrentState(new SlotBusyState());
		return parkingSlot;
	}

	@Override
	public List<Slot> findAllVacantSlots() {
		return this.parkingLot.getSlotList()
				.keySet()
				.stream()
				.filter(p->p.getCurrentState().getAvailablity())
				.collect(Collectors.toList());
	}
	@Override
	public ParkingTicket freeSlot(Slot slot) {
		slot.setCurrentState(new SlotAvailableState());
		this.parkingLot.getSlotList().replace(slot, null);

		Optional<ParkingTicket> ticket = parkingLot
				.getParkingTicketList()
				.stream()
				.filter(p ->p.getSlot().getCurrentTicket().equals(slot.getCurrentTicket()))
				.findAny();
		if(ticket.isPresent()){
			ticket.get().setEndTime(LocalDateTime.now());
			return ticket.get();
		}

		return null;
	}

	public Optional<Slot> getTicketForSlot(Vehicle car) {
		for (Entry<Slot, Vehicle> entry : this.parkingLot.getSlotList().entrySet()) {
			if (car.equals(entry.getValue())) {
				return Optional.of(entry.getKey());
			}
		}
		return Optional.empty();
	}

	public Optional<Slot> findSlotByCar(Vehicle car) {
		for (Entry<Slot, Vehicle> entry : this.parkingLot.getSlotList().entrySet()) {
			if (car.equals(entry.getValue())) {
				return Optional.of(entry.getKey());
			}
		}
		return Optional.empty();
	}
	
	public Optional<Vehicle> findCarBySlot(Slot slot) {
		return Optional.of(this.parkingLot.getSlotList().get(slot));
	}

	public ParkingTicket getTicket(Slot slot,String vehiclePlate) {
		return ParkingTicket.getTicketBuilder()
		.setSlot(slot)
		.setVehiclePlate(vehiclePlate)
		.setStartDateTime(LocalDateTime.now())
		.build();
	}
}
