package com.flay.cursomc4.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.flay.cursomc4.services.exceptions.ObjectNotFoundException;

//manipulador de Exception de recurso.
@ControllerAdvice
public class ResourceExceptionHandler { //manipulador de erros
	
	@ExceptionHandler(ObjectNotFoundException.class)//@ExceptionHandler: indica que é um tratador de exceção. Assinatura abaixo é obrigatória
	public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
		//HttpStatus.NOT_FOUND: objeto não encontrado.
		//System.currentTimeMillis(): horário local do sistema.
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(),
				System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}
}
