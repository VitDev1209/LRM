package com.acleda.controller;

import org.springframework.ui.Model;

public abstract class BaseController {

	protected void sendWarning(Model model, String message) {
		model.addAttribute("alertType", "warning");
		model.addAttribute("alertMessage", message);
	}
}
