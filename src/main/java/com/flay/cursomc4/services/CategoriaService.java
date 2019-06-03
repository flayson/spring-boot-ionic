package com.flay.cursomc4.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flay.cursomc4.domain.Categoria;
import com.flay.cursomc4.repositories.CategoriaRepository;
import com.flay.cursomc4.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	// esta dependencia será automaticamente instanciada pelo Spring. Mecanismo de
	// injeção de dependência ou de inversão de controle
	@Autowired
	private CategoriaRepository repositorio;

	public Categoria buscar(Integer id) {
//		Optional<Categoria> obj = repositorio.findById(id); // se o id não existe, retorna null
//		return obj.orElse(null);
		Optional<Categoria> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));


	}
}
