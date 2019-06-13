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

import com.tranporteMercadoria.mercurio.models.alterarSenha;
import com.tranporteMercadoria.mercurio.models.cadastroDeProdutoRealizado;
import com.tranporteMercadoria.mercurio.models.cadastroDeProdutos;
import com.tranporteMercadoria.mercurio.models.cadastroPessoas;
import com.tranporteMercadoria.mercurio.models.contaPessoas;
import com.tranporteMercadoria.mercurio.models.localizacaoPessoas;
import com.tranporteMercadoria.mercurio.repository.CadDeProdutoRealizadoRepository;
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
	
	@Autowired
	private CadDeProdutoRealizadoRepository cprr;
	
	
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
		ModelAndView mv = new ModelAndView(direcionarPaginaPorUsuario(conta.getNivel()));
		mv.addObject("contaUsuario", conta);
		mv.addObject("produtoEntrega", produto);
		return mv;
	}
	
	private String direcionarPaginaPorUsuario(long nivel) {
		if(nivel == 1) {
			return "visPedido";
		}else if(nivel == 2) {
			return "EfetuarEntrega";
		}else if(nivel == 3) {
			return "EfetuarEntrega";
		}
		else {
			return"paginaErro";
		}
	}
	
	@RequestMapping(value="/desativar/{idUsuario}", method=RequestMethod.GET)
	public String desativar(@PathVariable("idUsuario") long idUsuario, HttpServletRequest request) {
		cadastroPessoas pessoa = pr.findById(idUsuario);
		pessoa.setAtivo(1);
		pr.save(pessoa);
		return "RelClientesAtendente";
	}
	
	@RequestMapping(value="/ativar/{idUsuario}", method=RequestMethod.GET)
	public String ativar(@PathVariable("idUsuario") long idUsuario, HttpServletRequest request) {
		cadastroPessoas pessoa = pr.findById(idUsuario);
		pessoa.setAtivo(0);
		pr.save(pessoa);
		return "RelClientesAtendente";
	}

	
	@RequestMapping(value="/finalizarEntrega/{finalizar}", method=RequestMethod.GET)
	public ModelAndView finalizando(@PathVariable("finalizar") int finalizar, HttpServletRequest request, cadastroDeProdutoRealizado produtoRealizado){
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		cadastroDeProdutos produtoRealizados = cpr.findById(finalizar);
		produtoRealizados.setEntregaRealizado(1);
		cpr.save(produtoRealizados);
		Iterable<cadastroDeProdutos> produtoPedente = cpr.findAll();
		ModelAndView mv = new ModelAndView("pgiMotorista");
		mv.addObject("contaUsuario", conta);
		
		mv.addObject("produtoEntrega", produtoPedente);
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
	
	@RequestMapping(value="paginaInicialCliente")
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
	
	@RequestMapping(value="paginaInicialAtendente")
	public ModelAndView pgInicialAtendente(HttpServletRequest request){
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("pgiAtendente");
		Iterable<cadastroDeProdutos> listaDeProdutos = cpr.findAll();
		mv.addObject("contaLogado",conta);				
		mv.addObject("listaDeProdutos", listaDeProdutos);
		
		return mv;
		
		
	}
	
	@RequestMapping(value="paginaInicialMotorista")
	public ModelAndView pgInicialMotorista(HttpServletRequest request){
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("pgiMotorista");
		Iterable<cadastroDeProdutos> listaDeProdutos = cpr.findAll();
		mv.addObject("contaLogado",conta);				
		mv.addObject("listaDeProdutos", listaDeProdutos);
		
		return mv;
		
		
	}
	

	
	@RequestMapping(value="/cadastrar")
	public String form() {
		return "pessoas/formPessoas";
	}
	
	@RequestMapping(value="/relClientesGerente")
	public ModelAndView relatorioGerente(HttpServletRequest request,cadastroPessoas pessoas) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("relClientesGerente");
		mv.addObject("contaLogado",conta);
		Iterable<cadastroPessoas> listaPessoas = pr.findAll();
		mv.addObject("listaPessoas",listaPessoas);
		return mv;
	}
	
	@RequestMapping(value="/relEntregas")
	public ModelAndView form2(HttpServletRequest request,cadastroPessoas pessoas) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("relEntregas");
		mv.addObject("contaLogado",conta);
		return mv;
		
	}
	
	@RequestMapping(value="/relFuncionarios")
	public ModelAndView relFuncionarios(HttpServletRequest request,cadastroPessoas pessoas) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("relFuncionarios");
		mv.addObject("contaLogado",conta);
		Iterable<cadastroPessoas> listaPessoas = pr.findAll();
		mv.addObject("listaPessoas",listaPessoas);
		return mv;
	}
	
	@RequestMapping(value="/relatorioCliente")
	public ModelAndView relatorio(HttpServletRequest request,cadastroPessoas pessoas) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("RelClientesAtendente");
		mv.addObject("contaLogado",conta);
		Iterable<cadastroPessoas> listaPessoas = pr.findAll();
		mv.addObject("listaPessoas",listaPessoas);
		return mv;
	}
	
	@RequestMapping(value="/entrar", method=RequestMethod.POST)
	public ModelAndView form(HttpServletRequest request, contaPessoas conta) {
		
		if(confirmarAssinatura(conta)) {
			contaPessoas contas = cr.findByLogin(conta.getLogin());
			HttpSession session=request.getSession();
			session.setAttribute("usuario",contas.getId());
			if(contas.getNivel() == 3) {				
				ModelAndView mv = new ModelAndView("pgiAtendente");
				Iterable<cadastroDeProdutos> listaDeProdutos = cpr.findAll();
				mv.addObject("contaLogado",contas);				
				mv.addObject("listaDeProdutos", listaDeProdutos);
				
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
	
	@RequestMapping(value="/exibirMinhaInformacaoCliente", method=RequestMethod.GET)
	public ModelAndView exibirInformacao(HttpServletRequest request, cadastroPessoas pessoa) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("minhaInfoCliente");
		mv.addObject("contaLogado",conta);
		return mv;
	}
	
	@RequestMapping(value="/exibirMinhaInformacaoMotorista", method=RequestMethod.GET)
	public ModelAndView exibirInformacaoMotorista(HttpServletRequest request, cadastroPessoas pessoa) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("minhaInfoMotorista");
		mv.addObject("contaLogado",conta);
		return mv;
	}
	
	@RequestMapping(value="/exibirMinhaInformacaoAtendente", method=RequestMethod.GET)
	public ModelAndView exibirMinhaInformacaoAtendente(HttpServletRequest request, cadastroPessoas pessoa) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("minhaInfoAtendente");
		mv.addObject("contaLogado",conta);
		return mv;
	}
	
	@RequestMapping(value="/alterarInformacaoCliente", method=RequestMethod.GET)
	public ModelAndView alterarInformacao(HttpServletRequest request, cadastroPessoas pessoa) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("altPessoas");
		mv.addObject("contaLogado",conta);
		return mv;
	}
	
	@RequestMapping(value="/alterarCliente", method=RequestMethod.POST)
	public String alterarCliente(HttpServletRequest request, cadastroPessoas pessoas, contaPessoas contas) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		conta.getCadastro().setNome(pessoas.getNome());
		conta.getCadastro().setCpf(pessoas.getCpf());
		conta.getCadastro().setNascimento(pessoas.getNascimento());
		conta.getCadastro().setCelular(pessoas.getCelular());
		conta.getCadastro().setTelefone(pessoas.getTelefone());
		
		conta.setEmail(contas.getEmail());
		cr.save(conta);
		
		
		return "pgiCliente";
	}
	
	@RequestMapping(value="/altSenha", method=RequestMethod.GET)
	public ModelAndView altSenha(HttpServletRequest request, cadastroPessoas pessoa) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("altSenha");
		mv.addObject("contaLogado",conta);
		return mv;
	}
	
	@RequestMapping(value="/alterarSenha", method=RequestMethod.POST)
	public String alterarMotorista(HttpServletRequest request, cadastroPessoas pessoas, contaPessoas contas, alterarSenha senhanova) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		if(conta.getSenha().equals(senhanova.getSenhaAtual())) {
				if(senhanova.getSenhaNova().equals(senhanova.getConfirmandosenhaNova())) {
					conta.setSenha(senhanova.getSenhaNova());
					conta.setSenhaconfirma(senhanova.getConfirmandosenhaNova());
					cr.save(conta);
				}
		}
		return "pgiCliente";
	}
	
	@RequestMapping(value="/altSenhaAtd", method=RequestMethod.GET)
	public ModelAndView altSenhaAtd(HttpServletRequest request, cadastroPessoas pessoa) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("altSenhaAtendente");
		mv.addObject("contaLogado",conta);
		return mv;
	}
	
	@RequestMapping(value="/alterarSenhaAtd", method=RequestMethod.POST)
	public String alterarSenhaAtd(HttpServletRequest request, cadastroPessoas pessoas, contaPessoas contas, alterarSenha senhanova) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		if(conta.getSenha().equals(senhanova.getSenhaAtual())) {
				if(senhanova.getSenhaNova().equals(senhanova.getConfirmandosenhaNova())) {
					conta.setSenha(senhanova.getSenhaNova());
					conta.setSenhaconfirma(senhanova.getConfirmandosenhaNova());
					cr.save(conta);
				}
		}
		return "pgiAtendente";
	}
	
	@RequestMapping(value="/altSenhaMotorista", method=RequestMethod.GET)
	public ModelAndView altSenhaMotorista(HttpServletRequest request, cadastroPessoas pessoa) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		ModelAndView mv = new ModelAndView("altSenhaMotorista");
		mv.addObject("contaLogado",conta);
		return mv;
	}
	
	@RequestMapping(value="/alterarSenhaMotorista", method=RequestMethod.POST)
	public String alterarMotoristaMotorista(HttpServletRequest request, cadastroPessoas pessoas, contaPessoas contas, alterarSenha senhanova) {
		HttpSession httpSession = request.getSession(false);
		Long idDoUsuarioLogado = (Long) httpSession.getAttribute("usuario");
		contaPessoas conta = cr.findById(idDoUsuarioLogado);
		if(conta.getSenha().equals(senhanova.getSenhaAtual())) {
				if(senhanova.getSenhaNova().equals(senhanova.getConfirmandosenhaNova())) {
					conta.setSenha(senhanova.getSenhaNova());
					conta.setSenhaconfirma(senhanova.getConfirmandosenhaNova());
					cr.save(conta);
				}
		}
		return "pgiMotorista";
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
