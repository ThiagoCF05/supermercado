package com.difusores.supermercado.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MediatorController {

	@RequestMapping
	public String getRootPage() {
		return "redirect:supermercados/list";
	}
}
