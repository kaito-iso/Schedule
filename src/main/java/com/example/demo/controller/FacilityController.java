package com.example.demo.controller;

import java.util.List;

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

import com.example.demo.entity.Facility;
import com.example.demo.form.FacilityForm;
import com.example.demo.service.FacilityService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class FacilityController {

	private final FacilityService service;
	private static final String MODE = "mode";

	@GetMapping("/facilities")
	public String showFacilities(Model model) {

		List<Facility> facilities = service.findAll();

		model.addAttribute("facilities", facilities);

		return "admin_facilities";
	}

	@GetMapping("/facility/create")
	public String createFacilityForm(Model model) {

		model.addAttribute("mode", "create");
		model.addAttribute("facilityForm", new FacilityForm());

		return "admin_facility_form";
	}

	@GetMapping("/facility/edit/{facilityCode}")
	public String createFacilityEdit(@PathVariable String facilityCode, Model model) {

		Facility facility = service.findById(facilityCode)
				.orElseThrow(() -> new RuntimeException("施設が見つかりません"));

		FacilityForm form = new FacilityForm();
		form.setFacilityCode(facility.getFacilityCode());
		form.setFacilityName(facility.getFacilityName());
		form.setDisplayOrder(String.valueOf(facility.getDisplayOrder()));
		form.setActive(facility.isActive());

		model.addAttribute("mode", "edit");
		model.addAttribute("facilityForm", form);

		return "admin_facility_form";
	}

	@PostMapping("/facility/delete/{facilityCode}")
	public String deleteFacility(@PathVariable String facilityCode, RedirectAttributes redirectAttributes) {

		service.delete(facilityCode);
		redirectAttributes.addFlashAttribute("successMessage", "施設を削除しました。");

		return "redirect:/admin/facilities";
	}

	@PostMapping("/facility/save")
	public String saveFacility(@Validated @ModelAttribute("facilityForm") FacilityForm form,
			BindingResult result,
			@RequestParam(MODE) String mode,
			Model model,
			RedirectAttributes redirectAttributes) {

		// バリデーションエラー
		if (result.hasErrors()) {
			model.addAttribute("mode", mode);
			return "admin_facility_form";
		}

		try {
			service.save(form, mode);

			String message = "create".equals(mode) ? "新規登録が完了しました。" : "更新が完了しました。";
			model.addAttribute("successMessage", message);

			if ("create".equals(mode)) {
				model.addAttribute("mode", "create");
				model.addAttribute("facilityForm", new FacilityForm());
			} else {
				model.addAttribute("mode", "edit");
				model.addAttribute("facilityForm", form);
			}

		} catch (RuntimeException e) {

			model.addAttribute("mode", mode);
			model.addAttribute("facilityForm", form);
			result.rejectValue("facilityCode", "error.facilityCode", e.getMessage());
			return "admin_facility_form";

		} catch (Exception e) {

			e.printStackTrace();
			model.addAttribute("errorMessage", "予期せぬエラーが発生しました。");
			model.addAttribute("mode", mode);
			return "admin_facility_form";
		}

		return "admin_facility_form";
	}
}
