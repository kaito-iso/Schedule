package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Facility;
import com.example.demo.service.FacilityService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class FacilityController {

	private final FacilityService service;

	@GetMapping("/facilities")
	public String showFacilities(Model model) {

		List<Facility> facilities = service.findFacilities(true);

		model.addAttribute("facilities", facilities);

		return "admin_facilities";
	}
}
