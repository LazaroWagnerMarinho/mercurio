package com.tranporteMercadoria.mercurio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranporteMercadoria.mercurio.models.cadastroDeProdutos;

public interface CadProdutoRepository extends JpaRepository <cadastroDeProdutos, Long>{

}
