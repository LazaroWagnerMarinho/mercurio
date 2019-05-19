package com.tranporteMercadoria.mercurio.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="tb_cadastro_pessoas")
public class cadastroPessoas implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nome;
	private String cpf;
	private Date nascimento;
	private String celular;
	private String telefone;
	
	@OneToOne
	private localizacaoPessoas localizacao;
	
	@OneToOne
	private contaPessoas conta;
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public Date getNascimento() {
		return nascimento;
	}
	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public localizacaoPessoas getLocalizacao() {
		return localizacao;
	}
	public void setLocalizacao(localizacaoPessoas localizacao) {
		this.localizacao = localizacao;
	}
	public contaPessoas getConta() {
		return conta;
	}
	public void setConta(contaPessoas conta) {
		this.conta = conta;
	}


}
