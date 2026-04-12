package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.ScheduleCategory;
import com.example.demo.repository.ScheduleCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScheduleCategoryService {

	private final ScheduleCategoryRepository repository;

	public List<ScheduleCategory> findAll() {
		return repository.findByActive(true);
	}

}