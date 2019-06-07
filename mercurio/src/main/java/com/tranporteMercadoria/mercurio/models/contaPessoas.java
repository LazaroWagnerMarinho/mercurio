package com.tranporteMercadoria.mercurio.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class contaPessoas implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String email;
	private String login;
	private String senha;
	private String senhaconfirma;
	
	@OneToOne
	private cadastroPessoas cadastro;
	
	@OneToMany
	private List<cadastroDeProdutos> produtos;
	
		
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getSenhaconfirma() {
		return senhaconfirma;
	}
	public void setSenhaconfirma(String senhaconfirma) {
		this.senhaconfirma = senhaconfirma;
	}
	public cadastroPessoas getCadastro() {
		return cadastro;
	}
	public void setCadastro(cadastroPessoas cadastro) {
		this.cadastro = cadastro;
	}
	public List<cadastroDeProdutos> getProdutos() {
		return produtos;
	}
	public void setProdutos(List<cadastroDeProdutos> produtos) {
		this.produtos = produtos;
	}
	
	


}
