package com.tranporteMercadoria.mercurio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tranporteMercadoria.mercurio.models.contaPessoas;


public interface ContaRepository extends JpaRepository<contaPessoas, Long> {

}
