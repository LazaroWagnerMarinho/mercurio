package com.tranporteMercadoria.mercurio.controllers;

import java.util.List;
import java.util.Optional;

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
	
	
	@RequestMapping(value="cadastrarNovoProduto")
	public ModelAndView formCadEntrega(HttpServletRequest request){
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("formCadEntrega");
		mv.addObject("contaLogado",conta);
		
		return mv;
	}
	
	@RequestMapping(value="cadastrarNovoProdutoAtendente")
	public ModelAndView formCadEntregaAtendente(HttpServletRequest request){
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("formCadEntregaAtendente");
		mv.addObject("contaLogado",conta);
		
		return mv;
	}
	
	@RequestMapping("/{codigoProduto}")
	public ModelAndView EfetuarEntrega(@PathVariable("codigoProduto") long codigoProduto, HttpServletRequest request){
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		cadastroDeProdutos produto = cpr.findById(codigoProduto);
		ModelAndView mv = new ModelAndView("EfetuarEntrega");
		mv.addObject("contaUsuario", conta);
		mv.addObject("produtoEntrega", produto);
		return mv;
	}
	
	
	@RequestMapping(value="/continuarCadProduto", method=RequestMethod.POST)
	public ModelAndView resPedido(HttpServletRequest request,cadastroDeProdutos produtos) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		produtos.setConta(conta);
		cpr.save(produtos);
		ModelAndView mv = new ModelAndView("resPedido");
		Optional<com.tranporteMercadoria.mercurio.models.cadastroDeProdutos> infoDeProdutos = cpr.findById(produtos.getId());
		mv.addObject("contaUsuario", conta);
		mv.addObject("listaInformacoes",infoDeProdutos.get());
		return mv;
	}
	
	
	@RequestMapping(value="/resPedidoAtendente", method=RequestMethod.POST)
	public ModelAndView resPedidoAtendente(HttpServletRequest request,cadastroDeProdutos produtos) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		produtos.setConta(conta);
		cpr.save(produtos);
		ModelAndView mv = new ModelAndView("resPedidoAtendente");
		Optional<com.tranporteMercadoria.mercurio.models.cadastroDeProdutos> infoDeProdutos = cpr.findById(produtos.getId());
		mv.addObject("contaUsuario", conta);
		mv.addObject("listaInformacoes",infoDeProdutos.get());
		return mv;
	}	
	
	@RequestMapping(value="paginaInicial")
	public ModelAndView pgInicial(HttpServletRequest request){
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("pgiCliente");
		mv.addObject("contaLogado",conta);
		Iterable<cadastroDeProdutos> listaDeProdutos = cpr.findAll();
		mv.addObject("listaDeProdutosPorUsuario", listaDeProdutos);
		
		return mv;
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
			if(contas.getNivel() == 3) {				
				ModelAndView mv = new ModelAndView("pgiAtendente");
				mv.addObject("contaLogado",contas);
				
				return mv;
			}else if(contas.getNivel() == 2) {
				ModelAndView mv = new ModelAndView("pgiMotorista");
				Iterable<cadastroDeProdutos> listaDeProdutos = cpr.findAll();
				mv.addObject("contaLogado",contas);				
				mv.addObject("listaDeProdutos", listaDeProdutos);
				
				return mv;
			}else if(contas.getNivel() == 4) {
				ModelAndView mv = new ModelAndView("pgiGerente");
				mv.addObject("contaLogado",contas);
				
				return mv;
			}else {
				contaPessoas contaUsuario = cr.findById(contas.getId());
				ModelAndView mv = new ModelAndView("pgiCliente");
				mv.addObject("contaLogado",contas);		
				Iterable<cadastroDeProdutos> listaDeProdutos = cpr.findAll();
				mv.addObject("listaDeProdutosPorUsuario", listaDeProdutos);
				return mv;
			}			
			
		}else {
			return null;
		}
		
		
	}
	

	@RequestMapping(value="/cadastrar", method=RequestMethod.POST)
	public String form(cadastroPessoas pessoas, localizacaoPessoas localizacao, contaPessoas conta) {
		
		pr.save(pessoas);
		localizacao.setCadastro(pessoas);
		lr.save(localizacao);
		conta.setCadastro(pessoas);
		conta.setNivel(1);
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
