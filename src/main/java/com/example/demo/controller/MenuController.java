package com.example.demo.controller;

import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MenuController {

	private static final String date = "date";
	private final UserService userService;

	@GetMapping("/menu")
	public String menu(@RequestParam(name = date, required = false) String date, Model model, Principal principal) {

		// ユーザーID取得
		String userId = principal.getName();

		// ユーザー名取得
		String userName = userService.userName(userId);
		
		model.addAttribute("userName", userName);

		LocalDate baseDate;

		// パラメータの日付を使用、なければ今日
		if (date != null && !date.isEmpty()) {
			baseDate = LocalDate.parse(date);
		} else {
			baseDate = LocalDate.now();
		}

		// yyyy年MM月dd日(E)の形式で今日の日付を取得
		var formatterToday = DateTimeFormatter.ofPattern("yyyy年MM月dd日(E)", Locale.JAPANESE);
		model.addAttribute("today", LocalDate.now().format(formatterToday));

		// 日付計算
		model.addAttribute("prevWeek", baseDate.minusWeeks(1).toString()); // 前週
		model.addAttribute("prevDay", baseDate.minusDays(1).toString()); // 前日
		model.addAttribute("nextDay", baseDate.plusDays(1).toString()); // 翌日
		model.addAttribute("nextWeek", baseDate.plusWeeks(1).toString()); // 翌週

		List<LocalDate> days = new ArrayList<>();

		// スケジュール表示用の7日間
		for (int i = 0; i < 7; i++) {
			days.add(baseDate.plusDays(i));
		}
		model.addAttribute("days", days);

		return "menu";
	}

}
