package com.tranporteMercadoria.mercurio.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Tabela_Cad_Produto")
public class cadastroDeProdutos {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;	
	private String nome_Coleta;	
	private String contato_Tel;	
	private String cep_Coleta;	
	private String end_Coleta;	
	private int num_Coleta;	
	private String compl_Coleta;	
	private String bairro_Coleta;
	private String cidade_Coleta;
	private String uf_Coleta;
	
	private String nome_Entrega;	
	private String contato_Tel_Entrega;	
	private String cep_Entrega;	
	private String end_Entrega;	
	private int num_Entrega;	
	private String compl_Entrega;	
	private String bairro_Entrega;
	private String cidade_Entrega;
	private String uf_Entrega;
	
	private String tipo_Produtos;
	private double peso_Produto;
	private double altura_Produto;
	private double largura_Produto;
	private double Profundidade_Produto;
	
	@ManyToOne
	private contaPessoas conta;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome_Coleta() {
		return nome_Coleta;
	}
	public void setNome_Coleta(String nome_Coleta) {
		this.nome_Coleta = nome_Coleta;
	}
	public String getContato_Tel() {
		return contato_Tel;
	}
	public void setContato_Tel(String contato_Tel) {
		this.contato_Tel = contato_Tel;
	}
	public String getCep_Coleta() {
		return cep_Coleta;
	}
	public void setCep_Coleta(String cep_Coleta) {
		this.cep_Coleta = cep_Coleta;
	}
	public String getEnd_Coleta() {
		return end_Coleta;
	}
	public void setEnd_Coleta(String end_Coleta) {
		this.end_Coleta = end_Coleta;
	}
	public int getNum_Coleta() {
		return num_Coleta;
	}
	public void setNum_Coleta(int num_Coleta) {
		this.num_Coleta = num_Coleta;
	}
	public String getCompl_Coleta() {
		return compl_Coleta;
	}
	public void setCompl_Coleta(String compl_Coleta) {
		this.compl_Coleta = compl_Coleta;
	}
	public String getBairro_Coleta() {
		return bairro_Coleta;
	}
	public void setBairro_Coleta(String bairro_Coleta) {
		this.bairro_Coleta = bairro_Coleta;
	}
	
	public String getCidade_Coleta() {
		return cidade_Coleta;
	}
	public void setCidade_Coleta(String cidade_Coleta) {
		this.cidade_Coleta = cidade_Coleta;
	}
	public String getUf_Coleta() {
		return uf_Coleta;
	}
	public void setUf_Coleta(String uf_Coleta) {
		this.uf_Coleta = uf_Coleta;
	}
	public String getNome_Entrega() {
		return nome_Entrega;
	}
	public void setNome_Entrega(String nome_Entrega) {
		this.nome_Entrega = nome_Entrega;
	}
	public String getContato_Tel_Entrega() {
		return contato_Tel_Entrega;
	}
	public void setContato_Tel_Entrega(String contato_Tel_Entrega) {
		this.contato_Tel_Entrega = contato_Tel_Entrega;
	}
	public String getCep_Entrega() {
		return cep_Entrega;
	}
	public void setCep_Entrega(String cep_Entrega) {
		this.cep_Entrega = cep_Entrega;
	}
	public String getEnd_Entrega() {
		return end_Entrega;
	}
	public void setEnd_Entrega(String end_Entrega) {
		this.end_Entrega = end_Entrega;
	}
	public int getNum_Entrega() {
		return num_Entrega;
	}
	public void setNum_Entrega(int num_Entrega) {
		this.num_Entrega = num_Entrega;
	}
	public String getCompl_Entrega() {
		return compl_Entrega;
	}
	public void setCompl_Entrega(String compl_Entrega) {
		this.compl_Entrega = compl_Entrega;
	}
	public String getBairro_Entrega() {
		return bairro_Entrega;
	}
	public void setBairro_Entrega(String bairro_Entrega) {
		this.bairro_Entrega = bairro_Entrega;
	}
	
	public String getCidade_Entrega() {
		return cidade_Entrega;
	}
	public void setCidade_Entrega(String cidade_Entrega) {
		this.cidade_Entrega = cidade_Entrega;
	}
	public String getUf_Entrega() {
		return uf_Entrega;
	}
	public void setUf_Entrega(String uf_Entrega) {
		this.uf_Entrega = uf_Entrega;
	}
	
	public String getTipo_Produtos() {
		return tipo_Produtos;
	}
	public void setTipo_Produtos(String tipo_Produtos) {
		this.tipo_Produtos = tipo_Produtos;
	}
	public double getPeso_Produto() {
		return peso_Produto;
	}
	public void setPeso_Produto(double peso_Produto) {
		this.peso_Produto = peso_Produto;
	}
	public double getAltura_Produto() {
		return altura_Produto;
	}
	public void setAltura_Produto(double altura_Produto) {
		this.altura_Produto = altura_Produto;
	}
	public double getLargura_Produto() {
		return largura_Produto;
	}
	public void setLargura_Produto(double largura_Produto) {
		this.largura_Produto = largura_Produto;
	}
	public double getProfundidade_Produto() {
		return Profundidade_Produto;
	}
	public void setProfundidade_Produto(double profundidade_Produto) {
		Profundidade_Produto = profundidade_Produto;
	}
	public contaPessoas getConta() {
		return conta;
	}
	public void setConta(contaPessoas conta) {
		this.conta = conta;
	}
	
	
	
	
	
	
	

}
