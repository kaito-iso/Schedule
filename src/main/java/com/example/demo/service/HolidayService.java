package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Holiday;
import com.example.demo.repository.HolidayRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class HolidayService {

	private final HolidayRepository repository;

	public List<Holiday> findHolidays() {
		return repository.findByIsDeletedOrderByHolidayDateAsc(false);
	}

}
