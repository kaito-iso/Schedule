package com.example.demo.controller;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

import com.example.demo.entity.JobTitle;
import com.example.demo.form.JobTitleForm;
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

		return "admin_job_titles";
	}

	@GetMapping("/job-title/create")
	public String createJobTitleForm(Model model) {

		model.addAttribute("mode", "create");
		model.addAttribute("jobTitleForm", formActiveEnable());

		return "admin_job_title_form";
	}

	@GetMapping("/job-title/edit/{jobTitleCode}")
	public String createFacilityEdit(@PathVariable String jobTitleCode, Model model) {

		JobTitle jobTitle = service.findById(jobTitleCode)
				.orElseThrow(() -> new RuntimeException("施設が見つかりません"));

		JobTitleForm form = new JobTitleForm();
		form.setJobTitleCode(jobTitle.getJobTitleCode());
		form.setJobTitleName(jobTitle.getJobTitleName());
		form.setDisplayOrder(String.valueOf(jobTitle.getDisplayOrder()));
		form.setActive(jobTitle.isActive());

		model.addAttribute("mode", "edit");
		model.addAttribute("jobTitleForm", form);

		return "admin_job_title_form";
	}

	@PostMapping("/job-title/save")
	public String saveFacility(@Validated @ModelAttribute("jobTitleForm") JobTitleForm form,
			BindingResult result,
			@AuthenticationPrincipal UserDetails user,
			@RequestParam(MODE) String mode,
			Model model,
			RedirectAttributes redirectAttributes) {

		// バリデーションエラー
		if (result.hasErrors()) {
			model.addAttribute("mode", mode);
			return "admin_job_title_form";
		}

		try {

			String userId = user.getUsername();
			service.save(form, mode, userId);

			String message = "create".equals(mode) ? "新規登録が完了しました。" : "更新が完了しました。";
			model.addAttribute("successMessage", message);

			if ("create".equals(mode)) {
				model.addAttribute("mode", "create");
				model.addAttribute("jobTitleForm", formActiveEnable());
			} else {
				model.addAttribute("mode", "edit");
				model.addAttribute("jobTitleForm", form);
			}

		} catch (RuntimeException e) {

			model.addAttribute("mode", mode);
			model.addAttribute("jobTitleForm", form);
			result.rejectValue("jobTitleCode", "error.jobTitleCode", e.getMessage());
			return "admin_job_title_form";

		} catch (Exception e) {

			e.printStackTrace();
			model.addAttribute("errorMessage", "予期せぬエラーが発生しました。");
			model.addAttribute("mode", mode);
			return "admin_job_title_form";
		}

		return "admin_job_title_form";
	}

	public JobTitleForm formActiveEnable() {

		JobTitleForm form = new JobTitleForm();
		form.setActive(true);
		return form;

	}

}
