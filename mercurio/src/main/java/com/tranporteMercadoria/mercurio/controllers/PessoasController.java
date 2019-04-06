package com.tranporteMercadoria.mercurio.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PessoasController {
	
	@RequestMapping("/cadastrarPessoas")
	public String form() {
		return "pessoas/formPessoas";
	}

}
