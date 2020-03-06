package com.application.appointment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Data
@NoArgsConstructor
public class AppointmentResponse {
    private String appointmentId;
    private String startDateTime;
    private Duration duration;
    private Service service;
}
