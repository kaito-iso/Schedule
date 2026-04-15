package com.example.demo.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import com.example.demo.entity.composite.ScheduleParticipantId;

import lombok.Data;

@Entity
@Table(name = "t_schedule_participant")
@Data
public class ScheduleParticipant {

	@EmbeddedId
    private ScheduleParticipantId id;
}
