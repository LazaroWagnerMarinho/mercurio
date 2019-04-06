package com.tranporteMercadoria.mercurio.resources;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tranporteMercadoria.mercurio.models.cadastroPessoas;
import com.tranporteMercadoria.mercurio.repository.PessoasRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(value="/mer")

public class PessoasResource {
	
	@Autowired
	PessoasRepository pessoasRepository;
	
	@GetMapping("listarPessoas")
	public List<cadastroPessoas> listacadastroPessoas(){
		return pessoasRepository.findAll();
	}
	
	@PostMapping("/cadastropessoas")
	public cadastroPessoas salvaPessoas(@RequestBody @Valid cadastroPessoas pessoas) {
		return pessoasRepository.save(pessoas);
	}
	
	@DeleteMapping("excluirPessoas")
	public void deletaPessoas(@RequestBody @Valid cadastroPessoas deletaPessoas) {
		pessoasRepository.delete(deletaPessoas);
	}

}
