package com.tranporteMercadoria.mercurio;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/mercurio")
	public String index() {
		return "homeMercurio";
	}
	
	@RequestMapping("/index")
	public String index1() {
		return "pessoas/formPessoas";
	}
}
