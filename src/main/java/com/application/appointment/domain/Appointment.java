package com.application.appointment.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.Duration;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Appointment{
	@Id
	@GeneratedValue
	private long aId;
	private String id;
	private Doctor doctor;
	private Service service;
	private Duration durationInMinutes;
	private Location location;
	private String time;
}