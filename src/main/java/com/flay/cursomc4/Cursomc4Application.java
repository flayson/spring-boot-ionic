package com.flay.cursomc4;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flay.cursomc4.domain.Categoria;
import com.flay.cursomc4.domain.Produto;
import com.flay.cursomc4.repositories.CategoriaRepository;
import com.flay.cursomc4.repositories.ProdutoRepository;

@SpringBootApplication
public class Cursomc4Application implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Cursomc4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Eletrônica");

		Produto p1 = new Produto(null, "Mouse", 15.0);
		Produto p2 = new Produto(null, "Teclado", 24.0);
		Produto p3 = new Produto(null, "Raspberry", 80.0);

		Produto p4 = new Produto(null, "Capacitores", 7.0);
		Produto p5 = new Produto(null, "Resistores", 3.0);

		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p3, p4, p5));

		p1.getCategorias().add(cat1);
		p2.getCategorias().add(cat1);
		p3.getCategorias().addAll(Arrays.asList(cat1, cat2));

		p4.getCategorias().add(cat2);
		p5.getCategorias().add(cat2);
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

	}

}
