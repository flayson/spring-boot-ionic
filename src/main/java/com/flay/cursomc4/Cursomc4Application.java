package com.flay.cursomc4;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flay.cursomc4.domain.Categoria;
import com.flay.cursomc4.repositories.CategoriaRepository;

@SpringBootApplication
public class Cursomc4Application implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(Cursomc4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1  = new Categoria(null, "Informática");
		Categoria cat2  = new Categoria(null, "Eletrônica");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		
	}
	
	

}
