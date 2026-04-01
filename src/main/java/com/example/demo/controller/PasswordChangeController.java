package com.example.demo.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.exception.InvalidCurrentPasswordException;
import com.example.demo.form.PasswordChangeForm;
import com.example.demo.service.PasswordChangeService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class PasswordChangeController {

	private final PasswordChangeService service;

	@GetMapping("/password/change")
	public String passwordChange(@ModelAttribute PasswordChangeForm form) {
		return "password_change";
	}

	@PostMapping("/password/change")
	public String passwordChange(@Validated @ModelAttribute PasswordChangeForm form,
			BindingResult bindingResult,
			Principal principal,
			RedirectAttributes redirectAttributes) {

		// バリデーションチェック
		if (bindingResult.hasErrors()) {
			return "password_change";
		}

		// パスワードの一致チェック
		if (!form.getPassword1().equals(form.getPassword2())) {
			bindingResult.rejectValue("password2", "error.passwordMismatch", "パスワードが一致しません");
			return "password_change";
		}

		try {

			// Securityに保存されているIDを取得
			String userId = principal.getName();

			service.passwordChange(userId, form.getCurrentPassword(), form.getPassword1());

		} catch (InvalidCurrentPasswordException e) {

			// パスワードが違う場合の例外
			bindingResult.rejectValue("currentPassword", "error.invalidCurrentPassword", "現在のパスワードが正しくありません");
			return "password_change";

		} catch (Exception e) {

			// その他の例外
			redirectAttributes.addFlashAttribute("errorMessage", "システムエラーが発生しました");
			return "redirect:/password/change";
		}

		redirectAttributes.addFlashAttribute("successMessage", "パスワードを更新しました");
		return "redirect:/password/change";
	}
}
