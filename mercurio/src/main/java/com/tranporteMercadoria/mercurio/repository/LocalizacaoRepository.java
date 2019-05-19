package com.tranporteMercadoria.mercurio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranporteMercadoria.mercurio.models.localizacaoPessoas;

public interface LocalizacaoRepository extends JpaRepository<localizacaoPessoas, Long>{

}
