package com.tranporteMercadoria.mercurio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranporteMercadoria.mercurio.models.contaPessoas;


public interface ContaRepository extends JpaRepository<contaPessoas, String> {

	contaPessoas findById(long id);
	contaPessoas findByLogin(String login);

}
