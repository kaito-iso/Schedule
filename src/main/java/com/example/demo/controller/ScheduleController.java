package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	private static final String mode = "mode";
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
		form.setParticipants(List.of(userId));
		form.setIsPublic(true);

		model.addAttribute("scheduleForm", form);
		model.addAttribute("mode", "create");
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
	public String saveSchedule(@Validated @ModelAttribute("scheduleForm") ScheduleForm form,
			BindingResult result,
			@AuthenticationPrincipal UserDetails user,
			@RequestParam(name = mode, defaultValue = "create") String mode,
			Model model,
			RedirectAttributes redirectAttributes) {

		// バリデーションエラー
		if (result.hasErrors()) {
			return reloadForm(model, user.getUsername(), mode);
		}

		// 時刻入力の不完全チェック
		if (!form.getAllDay()) {
			if (form.getStartHour() == null || form.getStartMinute() == null ||
					form.getEndHour() == null || form.getEndMinute() == null) {
				result.rejectValue("startHour", "error.time.incomplete", "時刻を入力する場合は、開始・終了の時分をすべて入力してください");
			}
		}

		// 日時の前後関係チェック
		if (!result.hasErrors()) {
			LocalDateTime start = form.getStartDateTime();
			LocalDateTime end = form.getEndDateTime();
			if (!start.isBefore(end)) {
				result.rejectValue("endHour", "error.date.order", "終了日時は開始日時よりも後の時刻にしてください");
			}
		}

		// バリデーションの結果を再確認
		if (result.hasErrors()) {
			return reloadForm(model, user.getUsername(), mode);
		}

		System.out.println(form);

		return "redirect:/menu";
	}

	private String reloadForm(Model model, String userId, String mode) {
		model.addAttribute("mode", mode);
		model.addAttribute("years", yearService.findAll());
		model.addAttribute("months", IntStream.rangeClosed(1, 12).boxed().toList());
		model.addAttribute("daysInMonth", IntStream.rangeClosed(1, 31).boxed().toList());
		model.addAttribute("hours", IntStream.rangeClosed(0, 23).boxed().toList());
		model.addAttribute("minutes", MinuteInterval.getLabels());
		model.addAttribute("categorys", categoryService.findAll());
		model.addAttribute("loginUser",
				userService.findByUser(userId).orElseThrow(() -> new RuntimeException("User not found")));
		model.addAttribute("otherUsers", userService.otherUsers(userId));
		model.addAttribute("facilities", facilityservice.findByActive());
		return "schedule_form";
	}
}
