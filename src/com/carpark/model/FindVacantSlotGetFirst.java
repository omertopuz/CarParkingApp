package com.carpark.model;

import java.util.List;

public class FindVacantSlotGetFirst implements FindVacantSlotStrategy{

	@Override
	public Slot findVacantSlot(List<Slot> vacantSlotList) {
		checkVacantList(vacantSlotList);
		return vacantSlotList.get(0);
	}

}
