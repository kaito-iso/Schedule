package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.entity.JobTitle;
import com.example.demo.service.JobTitleService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class JobTitleController {

	private final JobTitleService service;
	private static final String MODE = "mode";

	@GetMapping("/job-titles")
	public String showJobTitles(Model model) {

		List<JobTitle> jobTitle = service.findAll();
		model.addAttribute("jobTitles", jobTitle);

		return "admin_jobTitles";
	}

}
