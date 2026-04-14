package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.User;
import com.example.demo.form.ScheduleForm;
import com.example.demo.service.FacilityService;
import com.example.demo.service.ScheduleCategoryService;
import com.example.demo.service.UserService;
import com.example.demo.service.YearService;
import com.example.demo.util.MinuteInterval;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ScheduleController {

	private static final String date = "date";
	private final YearService yearService;
	private final ScheduleCategoryService categoryService;
	private final FacilityService facilityservice;
	private final UserService userService;

	@GetMapping("/schedule/create")
	public String showCreateForm(@RequestParam(name = date, required = false) String dateStr, Model model,
			Principal principal) {

		LocalDate initialDate = Optional.ofNullable(dateStr)
				.filter(s -> !s.isEmpty())
				.map(s -> {
					try {
						return LocalDate.parse(s);
					} catch (DateTimeParseException e) {
						return null;
					}
				})
				.orElseGet(LocalDate::now);

		String userId = principal.getName();
		User loginUser = userService.findByUser(userId)
				.orElseThrow(() -> new RuntimeException("ログインユーザーが見つかりません"));

		// Formを作成し、初期値をセット
		ScheduleForm form = new ScheduleForm();
		form.setStartYear(initialDate.getYear());
		form.setStartMonth(initialDate.getMonthValue());
		form.setStartDay(initialDate.getDayOfMonth());
		form.setEndYear(initialDate.getYear());
		form.setEndMonth(initialDate.getMonthValue());
		form.setEndDay(initialDate.getDayOfMonth());
		form.setPublic(true);

		model.addAttribute("scheduleForm", form);
		model.addAttribute("selectedDate", initialDate);
		model.addAttribute("years", yearService.findAll());
		model.addAttribute("months", IntStream.rangeClosed(1, 12).boxed().toList());
		model.addAttribute("daysInMonth", IntStream.rangeClosed(1, 31).boxed().toList());
		model.addAttribute("hours", IntStream.rangeClosed(0, 23).boxed().toList());
		model.addAttribute("minutes", MinuteInterval.getLabels());
		model.addAttribute("categorys", categoryService.findAll());
		model.addAttribute("loginUser", loginUser);
		model.addAttribute("otherUsers", userService.otherUsers(userId));
		model.addAttribute("facilities", facilityservice.findByActive());

		return "schedule_form";
	}

	@PostMapping("/schedule/create")
	public String saveSchedule(@Validated @ModelAttribute("scheduleForm") ScheduleForm form) {
		System.out.println(form);
		return "redirect:/menu";
	}
}
