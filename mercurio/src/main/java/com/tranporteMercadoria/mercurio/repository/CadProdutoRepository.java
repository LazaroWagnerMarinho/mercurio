package com.tranporteMercadoria.mercurio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranporteMercadoria.mercurio.models.cadastroDeProdutos;
import com.tranporteMercadoria.mercurio.models.cadastroPessoas;

public interface CadProdutoRepository extends JpaRepository <cadastroDeProdutos, Long>{

	cadastroDeProdutos findById(long id);
}
