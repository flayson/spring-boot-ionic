package com.flay.cursomc4;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.flay.cursomc4.domain.Categoria;
import com.flay.cursomc4.domain.Cidade;
import com.flay.cursomc4.domain.Cliente;
import com.flay.cursomc4.domain.Endereco;
import com.flay.cursomc4.domain.Estado;
import com.flay.cursomc4.domain.Produto;
import com.flay.cursomc4.domain.enums.TipoCliente;
import com.flay.cursomc4.repositories.CategoriaRepository;
import com.flay.cursomc4.repositories.CidadeRepository;
import com.flay.cursomc4.repositories.ClienteRepository;
import com.flay.cursomc4.repositories.EnderecoRepository;
import com.flay.cursomc4.repositories.EstadoRepository;
import com.flay.cursomc4.repositories.ProdutoRepository;

@SpringBootApplication
public class Cursomc4Application implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;

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

		/* Estados e Cidades */
		Estado go = new Estado(null, "Goiás");
		Estado sp = new Estado(null, "São Paulo");
		Estado mg = new Estado(null, "Minas Gerais");

		Cidade c1 = new Cidade(null, "Inhumas", go);
		Cidade c2 = new Cidade(null, "São Paulo", sp);
		Cidade c3 = new Cidade(null, "Uberlândia", mg);
		Cidade c4 = new Cidade(null, "Caturaí", go);
		Cidade c5 = new Cidade(null, "Campinas", sp);

		go.getCidades().addAll(Arrays.asList(c1, c4));
		sp.getCidades().addAll(Arrays.asList(c2, c5));
		mg.getCidades().addAll(Arrays.asList(c3));

		estadoRepository.saveAll(Arrays.asList(go, sp, mg));//é importante gerar os estados para depois gerar as cidades, por causa das dependencias
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4, c5));
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@email.com", "12345", TipoCliente.PF);
		cli1.getTelefones().addAll(Arrays.asList("62125468", "56245878"));
		Endereco e1 = new Endereco(null, "Rua 1", "51", "Acima da biblioteca", "Centro", "75400-000", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua 3", "533", "Acima do banco", "Centro", "4350-000", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}

}
