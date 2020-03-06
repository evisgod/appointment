package com.application.appointment.domain;

import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Data
public class AppointmentsByLocation {
    private String locationName;
    private List<AppointmentResponse> appointments;

    public AppointmentsByLocation(){
        appointments = new LinkedList<>();
    }
}
