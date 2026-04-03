package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.IntStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Holiday;
import com.example.demo.form.HolidayForm;
import com.example.demo.service.HolidayService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class HolidayController {

	private static final String GET_YEAR = "getYear";
	private final HolidayService service;

	@GetMapping("/holidays")
	public String showHolidays(Model model) {

		int year = LocalDate.now().getYear();
		List<Integer> years = IntStream.rangeClosed(year, year + 2)
				.boxed()
				.toList();

		List<Holiday> holidays = service.findHolidays();

		model.addAttribute("years", years);
		model.addAttribute("holidays", holidays);

		return "admin_holidays";
	}

	@PostMapping("/holiday/import-api")
	public String importHolidaysApi(@RequestParam(GET_YEAR) int getYear, RedirectAttributes redirectAttributes) {

		try {
			service.importHolidaysApi(getYear);
			redirectAttributes.addFlashAttribute("successMessage", getYear + "年の祝日をインポートしました");
		} catch (Exception e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute("errorMessage", "通信エラーが発生しました");
		}

		return "redirect:/admin/holidays";
	}

	@GetMapping("/holiday/create")
	public String createHolidayForm(Model model) {
		model.addAttribute("holidayForm", new HolidayForm());
		return "admin_holiday_form";
	}

	@PostMapping("/holiday/create")
	public String createHolidayForm(@Validated @ModelAttribute HolidayForm form,
			BindingResult bindingResult,
			Model model,
			Principal principal,
			RedirectAttributes redirectAttributes) {

		// バリデーションチェック
		if (bindingResult.hasErrors()) {
			return "admin_holiday_form";
		}

		// 重複チェック
		if (service.existsById(form.getHolidayDate())) {
			bindingResult.rejectValue("holidayDate", "error.duplicate", "この日付は既に登録されています");
			return "admin_holiday_form";
		}

		// 保存
		service.save(form);

		model.addAttribute("holidayForm", new HolidayForm());

		redirectAttributes.addFlashAttribute("successMessage", "「" + form.getHolidayName() + "」を登録しました");
		return "redirect:/admin/holiday/create";
	}

	@PostMapping("/holiday/delete/{holidayDate}")
	public String deleteHoliday(@PathVariable("holidayDate") LocalDate date) {
		service.holidayDelete(date);
		return "redirect:/admin/holidays";
	}
}
