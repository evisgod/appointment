package com.application.appointment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Doctor {
	private String firstName;
    private String lastName;
}
