package com.flay.cursomc4.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flay.cursomc4.domain.Categoria;
import com.flay.cursomc4.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	//esta dependencia será automaticamente instanciada pelo Spring. Mecanismo de injeção de dependência ou de inversão de controle
	@Autowired 
	private CategoriaRepository repositorio;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repositorio.findById(id);
		return obj.orElse(null);
	}
}
