package com.tranporteMercadoria.mercurio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranporteMercadoria.mercurio.models.cadastroPessoas;

public interface PessoasRepository extends JpaRepository<cadastroPessoas, Long>{
	
	cadastroPessoas findById(long id); 

}
