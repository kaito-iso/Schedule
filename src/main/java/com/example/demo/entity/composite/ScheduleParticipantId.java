package com.example.demo.entity.composite;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class ScheduleParticipantId implements Serializable {

	@Column(name = "schedule_id")
	private Integer scheduleId;

	@Column(name = "user_id", length = 32)
	private String userId;
}