package com.tranporteMercadoria.mercurio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranporteMercadoria.mercurio.models.cadastroDeProdutoRealizado;

public interface CadDeProdutoRealizadoRepository extends JpaRepository <cadastroDeProdutoRealizado, Long> {

	cadastroDeProdutoRealizado findById(long id);
}
