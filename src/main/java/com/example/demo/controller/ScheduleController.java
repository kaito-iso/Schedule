package com.example.demo.controller;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.form.ScheduleForm;
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
		model.addAttribute("months", java.util.stream.IntStream.rangeClosed(1, 12).boxed().toList());
		model.addAttribute("daysInMonth", java.util.stream.IntStream.rangeClosed(1, 31).boxed().toList());
		model.addAttribute("hours", java.util.stream.IntStream.rangeClosed(0, 23).boxed().toList());
		model.addAttribute("minutes", MinuteInterval.getLabels());
		model.addAttribute("categorys", categoryService.findAll());

		return "schedule_form";
	}

	@PostMapping("/menu/create")
	public String createSchedule(@Validated @ModelAttribute ScheduleForm form,
			BindingResult result,
			Model model) {

		// バリデーション
		if (result.hasErrors()) {
			prepareModel(model);
			return "schedule_form";
		}

		try {
			// LocalDateTimeへの組み立て
			LocalDateTime startDateTime = LocalDateTime.of(
					form.getStartYear(), form.getStartMonth(), form.getStartDay(),
					form.getStartHour(), form.getStartMinute());

			LocalDateTime endDateTime = LocalDateTime.of(
					form.getEndYear(), form.getEndMonth(), form.getEndDay(),
					form.getEndHour(), form.getEndMinute());

			// 日時逆転チェック
			if (endDateTime.isBefore(startDateTime)) {
				result.rejectValue("endHour", "error.date.range", "終了日時は開始日時より後に設定してください");
				prepareModel(model);
				return "schedule_form";
			}
			
			System.out.println(form);

			// Serviceを呼んで保存

		} catch (DateTimeException e) {

			result.reject("error.invalid.date", "正しい日付を入力してください");
			prepareModel(model);
			return "schedule_form";
		}

		return "redirect:/menu";
	}

	private void prepareModel(Model model) {
		model.addAttribute("years", yearService.findAll());
		model.addAttribute("months", java.util.stream.IntStream.rangeClosed(1, 12).boxed().toList());
		model.addAttribute("hours", java.util.stream.IntStream.rangeClosed(0, 23).boxed().toList());
		model.addAttribute("minutes", MinuteInterval.getLabels());
		model.addAttribute("categorys", categoryService.findAll());
	}
}
