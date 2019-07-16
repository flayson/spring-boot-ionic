package com.flay.cursomc4.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flay.cursomc4.domain.Cliente;
import com.flay.cursomc4.resources.exceptions.StandardError;
import com.flay.cursomc4.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes") // chama-se endPoint
public class ClienteResource {
	/*
	 * Controladores REST costumam ter métodos pequenos, não é interessante colocar
	 * try cath nestes controladores.
	 */
	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable String id) {
		// ResponseEntity contém código de http de resposta. Várias informações de HTTP.
		// ResponseEntity<?> a interrogação significa que pode encontrar qualquer coisa.
		try {
			System.out.println("id: " + id);
			Integer num = Integer.parseInt(id);
			Cliente obj = service.buscar(num);
			System.out.println("N: " + num);
			return ResponseEntity.ok(obj);
		} catch (Exception e) {
			System.out.println("EX: " + e);
			// TODO: handle exception

			StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),
					System.currentTimeMillis());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
			// ok ==> resposta OK.
		}

	}
}
