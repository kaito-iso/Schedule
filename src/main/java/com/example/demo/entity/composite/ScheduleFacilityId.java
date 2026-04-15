package com.example.demo.entity.composite;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ScheduleFacilityId implements Serializable {

	@Column(name = "schedule_id")
	private Integer scheduleId;

	@Column(name = "facility_code", length = 5)
	private String facilityCode;
}