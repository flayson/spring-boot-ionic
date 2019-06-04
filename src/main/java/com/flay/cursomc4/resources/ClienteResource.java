package com.flay.cursomc4.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flay.cursomc4.domain.Cliente;
import com.flay.cursomc4.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {
	/*Controladores REST costumam ter métodos pequenos, não é interessante colocar try cath
	 * nestes controladores.
	 * */
	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		//ResponseEntity contém código de http de resposta. Várias informações de HTTP.
		//ResponseEntity<?> a interrogação significa que pode encontrar qualquer coisa.
		
		Cliente obj = service.buscar(id);
		
		//ok ==> resposta OK.
		return ResponseEntity.ok(obj);
		

	}
}
