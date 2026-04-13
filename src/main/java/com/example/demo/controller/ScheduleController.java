package com.example.demo.controller;

import java.time.LocalDate;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ScheduleCategoryService;
import com.example.demo.service.YearService;
import com.example.demo.util.MinuteInterval;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

	private static final String date = "date";
	private final YearService yearService;
	private final ScheduleCategoryService categoryService;

	@GetMapping("/menu/create")
	public String showCreateForm(@RequestParam(name = date, required = false) String dateStr, Model model) {

		LocalDate initialDate;
		try {
			// dataに値がなければ今日の日付を使用
			initialDate = (dateStr != null && !dateStr.isEmpty()) ? LocalDate.parse(dateStr) : LocalDate.now();
		} catch (Exception e) {
			// 例外が発生しても今日の日付を使用
			initialDate = LocalDate.now();
		}
		
		model.addAttribute("selectedDate", initialDate);
		model.addAttribute("years", yearService.findAll());
		model.addAttribute("months", java.util.stream.IntStream.rangeClosed(1, 12).boxed().toList());
		model.addAttribute("daysInMonth", java.util.stream.IntStream.rangeClosed(1, 31).boxed().toList());
		model.addAttribute("hours", java.util.stream.IntStream.rangeClosed(0, 23).boxed().toList());
		model.addAttribute("minutes", MinuteInterval.getLabels());
		model.addAttribute("categorys", categoryService.findAll());
		
		model.addAttribute("defaultHour", null);
		model.addAttribute("defaultMinute", null);

		return "schedule_form";
	}
}
