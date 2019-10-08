package com.flay.cursomc4.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flay.cursomc4.domain.Cliente;
import com.flay.cursomc4.repositories.ClienteRepository;
import com.flay.cursomc4.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	@Autowired
	private ClienteRepository repositorio;

	public Cliente find(Integer id) {
//		Optional<Categoria> obj = repositorio.findById(id); // se o id não existe, retorna null
//		return obj.orElse(null);
		Optional<Cliente> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));


	}
}
