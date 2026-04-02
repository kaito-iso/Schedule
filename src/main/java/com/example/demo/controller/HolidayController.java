package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.Holiday;
import com.example.demo.form.HolidayForm;
import com.example.demo.service.HolidayService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class HolidayController {

	private final HolidayService service;

	@GetMapping("/holidays")
	public String adminSetting(Model model) {

		List<Holiday> holidays = service.findHolidays();
		model.addAttribute("holidays", holidays);

		return "admin_holidays";
	}

	@GetMapping("/holiday/create")
	public String createHolidayForm(Model model) {
		model.addAttribute("holidayForm", new HolidayForm());
		return "admin_holiday_form";
	}

	@PostMapping("/holiday/create")
	public String createHolidayFormP(Model model) {
		model.addAttribute("holidayForm", new HolidayForm());
		return "admin_holiday_form";
	}
}
