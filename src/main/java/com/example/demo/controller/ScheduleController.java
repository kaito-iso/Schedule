package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.ScheduleCategoryService;
import com.example.demo.service.YearService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

	private static final String date = "date";
	private final YearService yearService;
	private final ScheduleCategoryService categoryService;

	@GetMapping("/menu/create")
	public String showCreateForm(@RequestParam(name = date, required = false) String dateStr, Model model) {

		// パースエラーを防ぐため、簡易的なチェックを入れるとより安全です
		LocalDate initialDate;
		try {
			initialDate = (dateStr != null && !dateStr.isEmpty()) ? LocalDate.parse(dateStr) : LocalDate.now();
		} catch (Exception e) {
			initialDate = LocalDate.now();
		}

		model.addAttribute("selectedDate", initialDate);

		model.addAttribute("selectedDate", initialDate);

		// セレクトボックス用のリスト
		model.addAttribute("years", yearService.findAll());
		model.addAttribute("months", java.util.stream.IntStream.rangeClosed(1, 12).boxed().toList());
		model.addAttribute("daysInMonth", java.util.stream.IntStream.rangeClosed(1, 31).boxed().toList());
		model.addAttribute("hours", java.util.stream.IntStream.rangeClosed(0, 23).boxed().toList());
		model.addAttribute("minutes", List.of("00", "15", "30", "45")); // 15分刻みの例
		model.addAttribute("categorys",categoryService.findAll());
		
		return "schedule_form";
	}
}
