package com.application.appointment.service;

import java.util.*;
import java.util.stream.Collectors;

import com.application.appointment.domain.*;
import com.application.appointment.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AppointmentService {

    @Value("${appointment.url}")
    private String serverUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Bean
    public RestTemplate restTemplate() {
        restTemplate = new RestTemplate();
        ((SimpleClientHttpRequestFactory) restTemplate.getRequestFactory()).setConnectTimeout(10000);
        return restTemplate;
    }

    public List<Data> getAllAppointments() {
        ResponseEntity<Appointment[]> response = restTemplate.exchange(serverUrl, HttpMethod.GET, null,
				Appointment[].class);
        if (response.getBody() != null) {
            List<Appointment> appointments = Arrays.asList(response.getBody());
            appointmentRepository.saveAll(appointments);
            return dataConverter(appointments);
        } else {
            throw new IllegalArgumentException("No record founds for the appointment. Please try again after some time.");
        }
    }

    public List<Data> getAppointmentByDoctor(String firstName, String lastName) {
        Doctor doctor = new Doctor(firstName, lastName);
        return dataConverter(appointmentRepository.findByDoctor(doctor));
    }

    public List<Data> getAppointmentByLocation(String location) {
        return dataConverter(appointmentRepository.findByLocation(location));
    }


    private List<Data> dataConverter(List<Appointment>  appointments) {
        Map<Doctor, Map<Location, List<Appointment>>> map = appointments.stream().collect(Collectors.groupingBy(
                Appointment::getDoctor,
                Collectors.groupingBy(Appointment::getLocation)));
        List<Data> dataList = new LinkedList<>();
        for (Map.Entry<Doctor, Map<Location, List<Appointment>>> entry : map.entrySet()) {
            Data data = new Data();
            data.setFirstName(entry.getKey().getFirstName());
            data.setLastName(entry.getKey().getLastName());
            List<AppointmentsByLocation> appointmentsByLocationList = new LinkedList<>();
            for (Map.Entry<Location, List<Appointment>> location : entry.getValue().entrySet()) {
                AppointmentsByLocation appointmentsByLocation = new AppointmentsByLocation();
                appointmentsByLocation.setLocationName(location.getKey().getName());
                List<AppointmentResponse> appointmentResponseList = new LinkedList<>();
                for (Appointment appointment : location.getValue()) {
                    AppointmentResponse appointmentResponse = new AppointmentResponse();
                    appointmentResponse.setAppointmentId(appointment.getId());
                    appointmentResponse.setStartDateTime(appointment.getTime());
                    appointmentResponse.setDuration(appointment.getDurationInMinutes().multipliedBy(60));
                    com.application.appointment.domain.Service service = new com.application.appointment.domain.Service();
                    service.setPrice(appointment.getService().getPrice());
                    service.setName(appointment.getService().getName());
                    appointmentResponse.setService(service);
                    appointmentResponseList.add(appointmentResponse);
                }
                appointmentsByLocation.setAppointments(appointmentResponseList);
                appointmentsByLocationList.add(appointmentsByLocation);
            }
            data.setAppointmentsByLocation(appointmentsByLocationList);
            dataList.add(data);
        }
        return dataList;
    }
}