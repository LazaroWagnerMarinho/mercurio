package com.tranporteMercadoria.mercurio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tranporteMercadoria.mercurio.models.cadastroPessoas;
import com.tranporteMercadoria.mercurio.repository.PessoasRepository;

@Controller
public class PessoasController {
	
	@Autowired
	private PessoasRepository pr;
	
	@RequestMapping(value="/cadastrarPessoas", method=RequestMethod.GET)
	public String form() {
		return "pessoas/formPessoas";
	}
	
	@RequestMapping(value="/cadastrarPessoas", method=RequestMethod.POST)
	public String form(cadastroPessoas pessoas) {
		
		pr.save(pessoas);
		
		return "redirect:/cadastrarPessoas";
	}
}
