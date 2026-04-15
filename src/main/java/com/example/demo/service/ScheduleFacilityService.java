package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ScheduleFacility;
import com.example.demo.entity.composite.ScheduleFacilityId;
import com.example.demo.repository.ScheduleFacilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleFacilityService {

	private final ScheduleFacilityRepository repository;

	public void save(Integer scheduleId, String facilityCode) {

		ScheduleFacilityId id = new ScheduleFacilityId();
		id.setScheduleId(scheduleId);
		id.setFacilityCode(facilityCode);

		ScheduleFacility sf = new ScheduleFacility();
		sf.setId(id);

		repository.save(sf);
	}
}
