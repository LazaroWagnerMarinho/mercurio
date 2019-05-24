package com.tranporteMercadoria.mercurio.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tranporteMercadoria.mercurio.models.cadastroPessoas;
import com.tranporteMercadoria.mercurio.models.contaPessoas;
import com.tranporteMercadoria.mercurio.models.localizacaoPessoas;
import com.tranporteMercadoria.mercurio.repository.ContaRepository;
import com.tranporteMercadoria.mercurio.repository.LocalizacaoRepository;
import com.tranporteMercadoria.mercurio.repository.PessoasRepository;

@Controller
public class PessoasController {
	@Autowired
	private PessoasRepository pr;
	
	@Autowired
	private LocalizacaoRepository lr;
	
	@Autowired
	private ContaRepository cr;
	
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.GET)
	public String form() {
		return "pessoas/formPessoas";
	}
	
	@RequestMapping("/formPontoA")
	public String pontoA() {
		return "pessoas/formPontoA";
	}
	
	@RequestMapping("/formPontoB")
	public String pontoB() {
		return "pessoas/formPontoB";
	}
	
	@RequestMapping(value="/cadastrarDados", method=RequestMethod.POST)
	public String form(cadastroPessoas pessoas, localizacaoPessoas localizacao, contaPessoas conta) {

		pr.save(pessoas);
		lr.save(localizacao);
		cr.save(conta);
		
		return "homeMercurio";
	}
	
	@RequestMapping("/listarPessoas")
	public ModelAndView cadastroPessoas() {
		ModelAndView mv = new ModelAndView("listaPessoas");
		Iterable<cadastroPessoas> cadastroPessoas = pr.findAll();
		mv.addObject("listaPessoa",cadastroPessoas);
		return mv;
	}

}
