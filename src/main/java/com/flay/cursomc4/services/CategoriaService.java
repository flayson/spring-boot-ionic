package com.flay.cursomc4.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.flay.cursomc4.domain.Categoria;
import com.flay.cursomc4.repositories.CategoriaRepository;
import com.flay.cursomc4.services.exceptions.DataIntegrityException;
import com.flay.cursomc4.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {
	// esta dependencia será automaticamente instanciada pelo Spring. Mecanismo de
	// injeção de dependência ou de inversão de controle
	@Autowired
	private CategoriaRepository repositorio;

	public Categoria find(Integer id) {
//		Optional<Categoria> obj = repositorio.findById(id); // se o id não existe, retorna null
//		return obj.orElse(null);
		Optional<Categoria> obj = repositorio.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));


	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); //Por garantia, será setado com o id null. O método save considerá como uma inserção
		// se o id não for null, o save atualizará a linha na tabela.
		return repositorio.save(obj);
	}
	
	public Categoria update(Categoria obj) {
		this.find(obj.getId());
		return repositorio.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
		repositorio.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new  DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
		}
	}
	
	public List<Categoria> findAll() {
		return repositorio.findAll();
		
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		
		return repositorio.findAll(pageRequest);
	}
}
