package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Year;
import com.example.demo.repository.YearRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class YearService {

	private final YearRepository repository;

	public List<Year> findAll() {
		return repository.findByActive(true);
	}

}