package com.carpark;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.carpark.business.ParkingManager;
import com.carpark.business.ParkingService;
import com.carpark.model.*;

public class CarParking {
	
	public static void main(String[] args) {
		CarParking carParking = new CarParking();

		ParkingService ps = new ParkingManager(new ParkingLot());
		
		carParking.createRandomSlots(ps,15);
		ps.addSlot(new Slot("W","75",74));

		List<Vehicle> cars = Stream.of(new Car("6-PYV-308")
		,new Car("450-AFM")
		,new Car("EB-588-PC")
		,new Car("294-YNH")).collect(Collectors.toList());


		List<Slot> busySlots = new ArrayList<>();

		busySlots.add(ps.parkCar(cars.get(0), new FindVacantSlotDefined("W", "75")));
		busySlots.add(ps.parkCar(cars.get(1), new FindVacantSlotGetFirst()));
		busySlots.add(ps.parkCar(cars.get(2), new FindVacantSlotNearest()));
		busySlots.add(ps.parkCar(cars.get(3), new FindVacantSlotRandom()));
//		busySlots.add(ps.parkCar(new Car("157-XWS"), new FindVacantSlotDefined("W", "75")));

		for (Vehicle c: cars) {
			Optional<Slot> s= ps.findSlotByCar(c);
			System.out.println(s.isPresent() ? s.get().toString() + " By Vehicle Plate : "+c.getVehiclePlate():"");
		}

		for (Slot s: busySlots) {
			Optional<Vehicle> c = ps.findCarBySlot(s);
			if (c.isPresent()){
				ParkingTicket ticket = ps.freeSlot(s);
				System.out.println(s.toString() + " - Free, unparked car : "+c.get().getVehiclePlate());
				System.out.println(ticket.toString());
			}
		}

		List<Slot> vacantSlots = ps.findAllVacantSlots();
		System.out.println("=============Vacant Slots================");
		for (Slot slot : vacantSlots) {
			System.out.println(slot.toString());
		}
	}
	
	private void createRandomSlots(ParkingService ps, int slotCount) {		
		for (int i = 0; i < slotCount; i++) {
			char letterAsciiCode = (char)(65 + (Math.random() * (90 - 65)));
			int numberCoordinate = (int) (Math.random() * 5);
			int randomDistanceFromGate = (int) (Math.random() * 100);
			ps.addSlot(new Slot(String.valueOf(letterAsciiCode),numberCoordinate+"",randomDistanceFromGate));
		}
	}
}
