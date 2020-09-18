package com.carpark.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class ParkingTicket {
	private UUID ticketBarcode;
	private Slot slot;
	private String vehiclePlate;
	private LocalDateTime startTime;
	private LocalDateTime endTime;

	private ParkingTicket(TicketBuilder ticketBuilder) {
		
		ticketBarcode = ticketBuilder.ticketBarcode;
		this.slot=ticketBuilder.slot;
		this.startTime = ticketBuilder.startTime;
		this.vehiclePlate = ticketBuilder.vehiclePlate;
	}
	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public Slot getSlot() {
		return slot;
	}

	public static TicketBuilder getTicketBuilder() {
		return new TicketBuilder();
	}

	@Override
	public String toString() {
		return "Ticket Info : {" +
				"ticketBarcode=" + ticketBarcode +
				", slot=" + slot.toString() +
				", vehiclePlate='" + vehiclePlate + '\'' +
				", startTime=" + startTime +
				", endTime=" + endTime +
				'}';
	}

	public static class TicketBuilder{
		private UUID ticketBarcode;
		private Slot slot;
		private LocalDateTime startTime;
		private String vehiclePlate;
		
		public TicketBuilder() {
			ticketBarcode = UUID.randomUUID();
		}
		
		public TicketBuilder setSlot(Slot slot) {
			this.slot = slot;
			this.slot.setCurrentTicket(ticketBarcode);
			return this;
		}

		public TicketBuilder setVehiclePlate(String vehiclePlate) {
			this.vehiclePlate = vehiclePlate;
			return this;
		}

		public TicketBuilder setStartDateTime(LocalDateTime dateTime) {
			this.startTime = dateTime;
			return this;
		}
		public ParkingTicket build() {
			return new ParkingTicket(this);
		}
	}
}
