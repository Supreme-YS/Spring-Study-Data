package com.study.anyang.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
@Slf4j
@Controller
@RequestMapping("/auth")
public class LoginController {

	@RequestMapping("/login")
	public String loginForm(String error, String logout, Model model) {
		log.info("#### LoginController > loginForm ####");
		if (error != null) {
			model.addAttribute("error", "Login Error!!!");
		}

		if (logout != null) {
			model.addAttribute("logout", "Logout!!!");
		}

		return "auth/loginForm";
	}
	
	@RequestMapping("/logout")
	public String logoutForm() {
		log.info("#### LoginController > logoutForm ####");

		return "auth/logoutForm";
	}

}
