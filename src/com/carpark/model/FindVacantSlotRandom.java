package com.carpark.model;

import java.util.List;

public class FindVacantSlotRandom implements FindVacantSlotStrategy{

	@Override
	public Slot findVacantSlot(List<Slot> vacantSlotList) {
		checkVacantList(vacantSlotList);
		
		int randomSlotIndex = (int) (Math.random() * (vacantSlotList.size()-1)) ;
		return vacantSlotList.get(randomSlotIndex);
	}

}
