package com.example.demo.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.example.demo.entity.composite.ScheduleFacilityId;

import lombok.Data;

@Entity
@Table(name = "t_schedule_facilitie")
@Data
public class ScheduleFacility {

	@EmbeddedId
	private ScheduleFacilityId id;
}
