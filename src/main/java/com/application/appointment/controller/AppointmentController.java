package com.application.appointment.controller;

import com.application.appointment.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.application.appointment.service.AppointmentService;

import java.util.List;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;
	
	@GetMapping
	public List<Data> getAllAppointments() {
		return appointmentService.getAllAppointments();
	}

	@GetMapping("/doctor")
	public List<Data> getAppointmentbyDoctor(@RequestParam String firstName, @RequestParam String lastName) {
		return appointmentService.getAppointmentByDoctor(firstName, lastName);
	}

	@GetMapping("/location")
	public List<Data> getAppointmentByLocation(@RequestParam String location){
		return appointmentService.getAppointmentByLocation(location);
	}
}