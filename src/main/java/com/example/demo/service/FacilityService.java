package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.repository.FacilityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacilityService {
	
	private final FacilityRepository repository;
}
