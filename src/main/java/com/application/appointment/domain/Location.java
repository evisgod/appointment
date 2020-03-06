package com.application.appointment.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Location {

	private String timeZoneCode;
    @Column(name="L_NAME")
    private String name;

    public Location(String name){
        this.name = name;
    }
}
