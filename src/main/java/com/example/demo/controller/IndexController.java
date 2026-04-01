package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.form.LoginForm;

@Controller
public class IndexController {

	@GetMapping("/")
	public String index(@ModelAttribute LoginForm form) {
		return "login";
	}
}
