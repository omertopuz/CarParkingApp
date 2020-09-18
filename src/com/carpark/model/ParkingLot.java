package com.carpark.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot{
	private int parkingLotId; 
	private Map<Slot,Vehicle> slotList;// must be synchronized in case of multithreading or use ConcurrentHahmap
	private List<ParkingTicket> parkingTicketList;// must be synchronized in case of multithreading or use CopyOnWriteArrayList
	private static int parkingLotIdCounter;
	
	public ParkingLot() {
		this.parkingLotId = generateId();
		this.slotList = new HashMap<Slot, Vehicle>();
		this.parkingTicketList = new ArrayList<ParkingTicket>();
	}	
	
	private synchronized int generateId() {
		return parkingLotIdCounter++;
	}
	
	public int getParkingLotId() {
		return parkingLotId;
	}

	public Map<Slot,Vehicle> getSlotList() {
		return slotList;
	}

	public List<ParkingTicket> getParkingTicketList() {
		return parkingTicketList;
	}
}
