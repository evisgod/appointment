package com.application.appointment.repository;

import com.application.appointment.domain.Appointment;
import com.application.appointment.domain.Doctor;
import com.application.appointment.domain.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AppointmentRepository extends CrudRepository<Appointment, Long> {
    List<Appointment> findByDoctor(Doctor doctor);

    @Query("SELECT a FROM Appointment a where a.location.name=:loc")
    List<Appointment> findByLocation(@Param("loc") String loc);
}
