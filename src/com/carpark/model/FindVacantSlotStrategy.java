package com.carpark.model;

import java.util.List;

/*
* Strategy design pattern implementation
* */

public interface FindVacantSlotStrategy {
	
	Slot findVacantSlot(List<Slot> vacantSlotList);
	
	default void checkVacantList(List<Slot> vacantSlotList){ 
		if(vacantSlotList == null || (vacantSlotList!= null && vacantSlotList.size()==0))
			throw new RuntimeException("There is no available slot!");
		
    }
}
