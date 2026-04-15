package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ScheduleParticipant;
import com.example.demo.entity.composite.ScheduleParticipantId;
import com.example.demo.repository.ScheduleParticipantRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleParticipantService {
	
	private final ScheduleParticipantRepository repository;
	
	public void save(Integer scheduleId, String userId) {

		ScheduleParticipantId id = new ScheduleParticipantId();
		id.setScheduleId(scheduleId);
		id.setUserId(userId);

		ScheduleParticipant sp = new ScheduleParticipant();
		sp.setId(id);

		repository.save(sp);
	}

}
