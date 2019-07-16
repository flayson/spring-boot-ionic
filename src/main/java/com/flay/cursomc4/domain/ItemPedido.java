package com.flay.cursomc4.domain;
//implentacao N:M

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {
	@JsonIgnore //não será serealizado, não precisa utilizar mais jsonmaneged reference e jsonbackreference
	@EmbeddedId //Embutir de um tipo auxiliar "embededaid"
	private ItemPedidoPK id = new ItemPedidoPK();//as associacoes forem feitas no itempedidoPK
	
	private Double desconto;
	private Integer quantidade;
	private Double preco;
	
	private static final long serialVersionUID = 1L;
	
	public ItemPedido() {

	}
	
	public ItemPedido(Pedido pedido, Produto produto, Double desconto, Integer quantidade, Double preco) {
		super();
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	//não faz muito sentido informar o itemPedidoPK, pois o valor real é o pedido e o produto.
	public ItemPedido(ItemPedidoPK id, Double desconto, Integer quantidade, Double preco) {
		super();
		this.id = id;
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	//para ter acesso direto ao pedido. Este método faz referência cíclica. Tudo que começa com get, o JPA entende que tem que serealizar
	@JsonIgnore //neste caso tem que usar o jsonIgnore
	public Pedido getPedido() {
		return getId().getPedido();
	}
	//para ter acesso direto ao produto. Este método faz referência cíclica. Tudo que começa com get, o JPA entende que tem que serealizar
	public Produto getProduto() { 
		return getId().getProduto();
	}
	public Double getDesconto() {
		return desconto;
	}
	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public Double getPreco() {
		return preco;
	}
	public void setPreco(Double preco) {
		this.preco = preco;
	}
	
	public ItemPedidoPK getId() {
		return id;
	}

	public void setId(ItemPedidoPK id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((desconto == null) ? 0 : desconto.hashCode());
		result = prime * result + ((preco == null) ? 0 : preco.hashCode());
		result = prime * result + ((quantidade == null) ? 0 : quantidade.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (desconto == null) {
			if (other.desconto != null)
				return false;
		} else if (!desconto.equals(other.desconto))
			return false;
		if (preco == null) {
			if (other.preco != null)
				return false;
		} else if (!preco.equals(other.preco))
			return false;
		if (quantidade == null) {
			if (other.quantidade != null)
				return false;
		} else if (!quantidade.equals(other.quantidade))
			return false;
		return true;
	}
}
