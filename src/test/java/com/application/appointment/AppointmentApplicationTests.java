package com.application.appointment;

import com.application.appointment.controller.AppointmentController;
import com.application.appointment.domain.*;
import com.application.appointment.repository.AppointmentRepository;
import com.application.appointment.service.AppointmentService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.runner.RunWith;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class AppointmentApplicationTests {

    @Autowired
    private AppointmentController appointmentController;
    @MockBean
    private AppointmentService appointmentService;
    @MockBean
    private AppointmentRepository appointmentRepository;
    @Mock
    private RestTemplate restTemplate;
    private Appointment appointment;
    private  Appointment[] as = new Appointment[5];

    @Before
    public void setup(){


     }

    @Test
    public void getAllAppointmentTest(){
        Doctor doctor = new Doctor("Annabell", "Hilll");
        Service service = new Service("5066", "Enema");
        Location location = new Location("America/Chicago", "KC Doctors" );
        appointment = new Appointment().builder().id("52cca986-951f-4e5d-8750-a6b72fd02532").doctor(doctor).
                durationInMinutes(Duration.ofMinutes(14)).location(location).service(service).time("2020-02-21 10:37:00").build();
        as[0] = appointment;
        System.out.print(as[0]);
        Mockito.when(restTemplate.exchange("https://us-central1-sesame-care-dev.cloudfunctions.net/sesame_programming_test_api", HttpMethod.GET, null,
                Appointment[].class)).thenReturn(new ResponseEntity<>(as, HttpStatus.OK));
        List<Data> data = appointmentController.getAllAppointments();
        Assert.assertEquals("Annabell", data.get(0).getFirstName());
    }


}
