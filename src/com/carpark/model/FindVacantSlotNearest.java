package com.carpark.model;

import java.util.Comparator;
import java.util.List;

public class FindVacantSlotNearest implements FindVacantSlotStrategy {

	@Override
	public Slot findVacantSlot(List<Slot> vacantSlotList) {
		checkVacantList(vacantSlotList);
		vacantSlotList.sort(Comparator.comparing(Slot::getDistanceFromGate));
		return vacantSlotList.get(0);
	}

}
