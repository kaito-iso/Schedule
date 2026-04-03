package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import com.example.demo.service.FacilityService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class FacilityController {

	private final FacilityService service;
}
