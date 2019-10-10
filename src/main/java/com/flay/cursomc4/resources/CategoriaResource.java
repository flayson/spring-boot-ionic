package com.flay.cursomc4.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

//import java.util.ArrayList;
//import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.flay.cursomc4.domain.Categoria;
import com.flay.cursomc4.services.CategoriaService;
import com.flay.cursomc4.services.dto.CategoriaDTO;

@RestController
@RequestMapping(value = "/categorias") // chama-se endPoint
public class CategoriaResource {
	/*
	 * Controladores REST costumam ter métodos pequenos, não é interessante colocar
	 * try cath nestes controladores.
	 */
	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
//	public ResponseEntity<?> find(@PathVariable Integer id) {
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {// pode especificar o tipo: Categoria
		// ResponseEntity contém código de http de resposta. Várias informações de HTTP.
		// ResponseEntity<?> a interrogação significa que pode encontrar qualquer coisa.

		Categoria obj = service.find(id);

		// ok ==> resposta OK.
//		return ResponseEntity.ok(obj);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Categoria obj) { // void retorna o corpo vazio. // Request Body, faz
																		// o objeto json ser convertido em objeto java
		obj = service.insert(obj); // insert rertona um obj.
		// codigo de resposta HTTP para inserção: 201 Created. Conforme a W3. Deve
		// retornar a uri do novo recurso criado, ou seja, o id da nova categoria.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build(); // gera o código 201.
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@RequestBody Categoria obj, @PathVariable Integer id) { // basico para update
		obj.setId(id);// garantia para informar o id
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

//	@RequestMapping(method = RequestMethod.GET) //endereco/categorias   {sem id}
//	public ResponseEntity<List<Categoria>> findAll1() {	
//		List<Categoria> list = service.findAll();
//		return ResponseEntity.ok().body(list);
//		
//	}

	@RequestMapping(method = RequestMethod.GET) // endereco/categorias {sem id}
	public ResponseEntity<List<CategoriaDTO>> findAll() {

		List<Categoria> list = service.findAll();
		// obj-> operador de
		List<CategoriaDTO> listDTO = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	//http://localhost:9090/categorias/page?linesPerPage=5&page=1&direction=DESC
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "ordeBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Categoria> list = service.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDTO);
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

//	public String listar2() {
//		Categoria cat1 = new Categoria(1, "Informática");
//		Categoria cat2 = new Categoria(2, "Eletrônica");
//
//		List lista = new ArrayList();
//		lista.add(cat1);
//		lista.add(cat2);
//		return "Rest OK!";
//	}
}
