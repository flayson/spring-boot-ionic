package com.flay.cursomc4;

import java.text.SimpleDateFormat;
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
import com.flay.cursomc4.domain.ItemPedido;
import com.flay.cursomc4.domain.Pagamento;
import com.flay.cursomc4.domain.PagamentoComBoleto;
import com.flay.cursomc4.domain.PagamentoComCartao;
import com.flay.cursomc4.domain.Pedido;
import com.flay.cursomc4.domain.Produto;
import com.flay.cursomc4.domain.enums.EstadoPagamento;
import com.flay.cursomc4.domain.enums.TipoCliente;
import com.flay.cursomc4.repositories.CategoriaRepository;
import com.flay.cursomc4.repositories.CidadeRepository;
import com.flay.cursomc4.repositories.ClienteRepository;
import com.flay.cursomc4.repositories.EnderecoRepository;
import com.flay.cursomc4.repositories.EstadoRepository;
import com.flay.cursomc4.repositories.ItemPedidoRepository;
import com.flay.cursomc4.repositories.PagamentoRepository;
import com.flay.cursomc4.repositories.PedidoRepository;
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
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository; //não há necessidade de criar pagamentoBoleto e pagamentoCartao, pois estas classes herdam de pagamento.	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	public static void main(String[] args) {
		SpringApplication.run(Cursomc4Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Eletrônica");
		Categoria cat3 = new Categoria(null, "Cama Mesa e Banho");
		Categoria cat4 = new Categoria(null, "Jardinagem");
		Categoria cat5 = new Categoria(null, "Perfumaria");
		Categoria cat6 = new Categoria(null, "Automobilistica");
		Categoria cat7 = new Categoria(null, "Limpeza");
		
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
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
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
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		/*Obs.: pagamento e pedido são independes um do outro, neste caso, retirou-se o pagamento em pedido para permitir criar o pedido.*/
		Pedido ped1 = new Pedido(null, sdf.parse("04/06/2019 19:35"), cli1, e1 );
		Pedido ped2 = new Pedido(null, sdf.parse("08/08/2019 22:35"), cli1, e2 );
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QU, ped1, 6);
		ped1.setPagamento(pagto1);

		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PE, ped2, sdf.parse("09/10/2019 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().add(ip3);
		
		p1.getItens().add(ip1);
		p2.getItens().add(ip3);
		p3.getItens().add(ip2);
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
		
	}

}
