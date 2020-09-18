package com.carpark.model;

import java.util.List;
import java.util.Optional;

public class FindVacantSlotDefined implements FindVacantSlotStrategy{

	private String customAddress;

	public FindVacantSlotDefined(String letterCoordinate, String numberCoordinate) {
		customAddress = new Slot(letterCoordinate,numberCoordinate).getCustomAdress();
	}

	@Override
	public Slot findVacantSlot(List<Slot> vacantSlotList) {
		checkVacantList(vacantSlotList);
		Optional<Slot> slot = vacantSlotList.stream().filter(p->p.getCustomAdress().equals(customAddress)).findAny();
		
		if(slot.isPresent())
			return slot.get();
		else {
			throw new RuntimeException("There is no available vacant slot in location of "+this.customAddress);
		}
	}

}
