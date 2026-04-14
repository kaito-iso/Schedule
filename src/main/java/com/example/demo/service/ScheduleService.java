package com.example.demo.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Schedule;
import com.example.demo.form.ScheduleForm;
import com.example.demo.repository.ScheduleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleService {

	private final ScheduleRepository repository;

	public void save(ScheduleForm from, String mode, String userId) {

		Schedule schedule;

		if ("edit".equals(mode)) {
			schedule = new Schedule();
			/*
			schedule = repository.findById(from.getId())
					.orElseThrow(() -> new RuntimeException("不正なリクエストです：更新対象が存在しません"));
			
			schedule.setUpdDate(LocalDateTime.now());
			schedule.setUpdCode(userId);
			*/

		} else if ("create".equals(mode)) {

			schedule = new Schedule();

			schedule.setAddDate(LocalDateTime.now());
			schedule.setAddCode(userId);

		} else {
			throw new IllegalArgumentException("無効なモードです: " + mode);
		}

		schedule.setCategoryCode(from.getCategoryCode());
		schedule.setTitle(from.getTitle());
		schedule.setContent(from.getContent());
		schedule.setStartDate(from.getStartDateTime());
		schedule.setEndDate(from.getEndDateTime());
		schedule.setIsAllDay(from.getAllDay());
		schedule.setIsPublic(from.getIsPublic());
		schedule.setMeetingUrl(from.getMeetingUrl());

		repository.save(schedule);
	}

}
