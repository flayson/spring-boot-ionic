package com.flay.cursomc4.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flay.cursomc4.domain.Categoria;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Eletrônica");
		
		List lista = new ArrayList();
		lista.add(cat1);
		lista.add(cat2);
		
		return lista;
		
	}
	@RequestMapping(value="/listar2")
	public String listar2() {
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Eletrônica");
		
		List lista = new ArrayList();
		lista.add(cat1);
		lista.add(cat2);
		return "Rest OK!";
	}
}
