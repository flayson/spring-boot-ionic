package com.flay.cursomc4.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flay.cursomc4.domain.Pedido;
import com.flay.cursomc4.repositories.PedidoRepository;
import com.flay.cursomc4.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository repositorio;

	public Pedido buscar(Integer id) {
//		Optional<Pedido> obj = repositorio.findById(id); // se o id não existe, retorna null
//		return obj.orElse(null);
		Optional<Pedido> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
