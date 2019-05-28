package com.tranporteMercadoria.mercurio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.tranporteMercadoria.mercurio.models.contaPessoas;
import com.tranporteMercadoria.mercurio.repository.ContaRepository;

@Repository
public class ImplementsUserDetailsService implements UserDetailsService {

	@Autowired
	private ContaRepository cr;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		contaPessoas conta = cr.findByLogin(login);
		
		if(conta == null) {
			throw new UsernameNotFoundException("Usuario não encontrado");
		}
		return conta;
	}

}
