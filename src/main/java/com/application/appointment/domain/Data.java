package com.application.appointment.domain;

import lombok.AllArgsConstructor;

import java.util.LinkedList;
import java.util.List;

@lombok.Data
public class Data {
	private String firstName;
	private String lastName;
	private List<AppointmentsByLocation> appointmentsByLocation;

	public Data(){
		appointmentsByLocation = new LinkedList<>();
	}
}
