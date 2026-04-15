package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.ScheduleParticipant;
import com.example.demo.entity.composite.ScheduleParticipantId;

public interface ScheduleParticipantRepository extends JpaRepository<ScheduleParticipant, ScheduleParticipantId> {

}
