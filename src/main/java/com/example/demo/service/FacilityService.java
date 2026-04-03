package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Facility;
import com.example.demo.repository.FacilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacilityService {

	private final FacilityRepository repository;
	
	public List<Facility> findFacilities(boolean acitive) {
		return repository.findByActiveOrderByDisplayOrder(acitive);
	}
}
