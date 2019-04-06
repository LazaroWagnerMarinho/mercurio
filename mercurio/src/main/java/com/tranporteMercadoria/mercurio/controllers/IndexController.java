package com.tranporteMercadoria.mercurio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/mercurio")
	public String index() {
		return "homeMercurio";
	}
}
