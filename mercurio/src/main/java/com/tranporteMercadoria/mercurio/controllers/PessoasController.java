package com.tranporteMercadoria.mercurio.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.tranporteMercadoria.mercurio.models.cadastroDeProdutos;
import com.tranporteMercadoria.mercurio.models.cadastroPessoas;
import com.tranporteMercadoria.mercurio.models.contaPessoas;
import com.tranporteMercadoria.mercurio.models.localizacaoPessoas;
import com.tranporteMercadoria.mercurio.repository.CadProdutoRepository;
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
	
	@Autowired
	private CadProdutoRepository cpr;
	
	
	@RequestMapping(value="{contaId}")
	public ModelAndView formCadEntrega(@PathVariable("contaId") long contaId){
		
		ModelAndView mv = new ModelAndView("formCadEntrega");
		
		contaPessoas contas = cr.findById(contaId);
		mv.addObject("contaLogado",contas);
		
		return mv;
//		return "pessoas/formCadEntrega";
	}
	
	@RequestMapping(value="/continuarCadProduto", method=RequestMethod.POST)
	public String resPedido(HttpServletRequest request,cadastroDeProdutos produtos, contaPessoas conta) {
		HttpSession httpSession = request.getSession(false);
		Object idDoUsuarioLogado = httpSession.getAttribute("usuario");
		
//		produtos.setConta(conta);
		cpr.save(produtos);
//		ModelAndView mv = new ModelAndView("continuarCadProduto");
//		Iterable<cadastroDeProdutos> listaDeProdutos = cpr.findAll();
//		mv.addObject("listaInformacoes",listaDeProdutos);
		return "resPedido";
	}
	
	
	@RequestMapping(value="/cadastrar")
	public String form() {
		return "pessoas/formPessoas";
	}
	
	@RequestMapping(value="/entrar", method=RequestMethod.POST)
	public ModelAndView form(HttpServletRequest request, contaPessoas conta) {
		
		if(confirmarAssinatura(conta)) {
			contaPessoas contas = cr.findByLogin(conta.getLogin());
			HttpSession session=request.getSession();
			session.setAttribute("usuario",contas.getId());
	        
			ModelAndView mv = new ModelAndView("pgiCliente");
//			contaPessoas contas = cr.findByLogin(conta.getLogin());
			mv.addObject("contaLogado",contas);
			
			return mv;
		}else {
			return null;
		}
		
		
	}
	
	
	
	@RequestMapping("/pontoA")
	public String pontoA() {
		return "pessoas/formPontoA";
	}
	
	@RequestMapping("/pontoB")
	public String pontoB() {
		return "pessoas/formPontoB";
	}
	
//	@RequestMapping("/index")
//	public String index() {
//		return "pessoas/index";
//	}
	
	
	
	@RequestMapping(value="/cadastrar", method=RequestMethod.POST)
	public String form(cadastroPessoas pessoas, localizacaoPessoas localizacao, contaPessoas conta) {
		
		pr.save(pessoas);
		localizacao.setCadastro(pessoas);
		lr.save(localizacao);
		conta.setCadastro(pessoas);
		cr.save(conta);
		
		return "homeMercurio";
	}
	
	@RequestMapping(value= "/listarPessoas", method=RequestMethod.GET)
	public ModelAndView cadastroPessoas() {
		ModelAndView mv = new ModelAndView("listaPessoas");
		Iterable<cadastroPessoas> cadastroPessoas = pr.findAll();
		mv.addObject("listaPessoa",cadastroPessoas);
		return mv;
	}
	
	@RequestMapping(value= "/listarDeProdutos", method=RequestMethod.GET)
	public ModelAndView cadastroDeProdutos() {
		ModelAndView mv = new ModelAndView("resPedido");
		Iterable<cadastroDeProdutos> listaDeProdutos = cpr.findAll();
		mv.addObject("listaInformacoes",listaDeProdutos);
		return mv;
	}
	
	private boolean confirmarAssinatura(contaPessoas conta) {
		contaPessoas contas = cr.findByLogin(conta.getLogin());
		if(contas.getSenha().equals(conta.getSenha())) {
			return true;
		}else {
			return false;
		}
		
	}
	
}
