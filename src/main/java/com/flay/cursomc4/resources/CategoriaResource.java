package com.flay.cursomc4.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flay.cursomc4.domain.Categoria;
import com.flay.cursomc4.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	/*Controladores REST costumam ter métodos pequenos, não é interessante colocar try cath
	 * nestes controladores.
	 * */
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		//ResponseEntity contém código de http de resposta. Várias informações de HTTP.
		//ResponseEntity<?> a interrogação significa que pode encontrar qualquer coisa.
		
		Categoria obj = service.buscar(id);
		
		//ok ==> resposta OK.
		return ResponseEntity.ok(obj);
		

	}

//	public List<Categoria> listar() {
//		Categoria cat1 = new Categoria(1, "Informática");
//		Categoria cat2 = new Categoria(2, "Eletrônica");
//		
//		List lista = new ArrayList();
//		lista.add(cat1);
//		lista.add(cat2);
//		
//		return lista;
//		
//	}

	public String listar2() {
		Categoria cat1 = new Categoria(1, "Informática");
		Categoria cat2 = new Categoria(2, "Eletrônica");

		List lista = new ArrayList();
		lista.add(cat1);
		lista.add(cat2);
		return "Rest OK!";
	}
}
