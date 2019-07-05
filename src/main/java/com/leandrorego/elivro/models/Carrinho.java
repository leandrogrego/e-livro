package com.leandrorego.elivro.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Carrinho implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//um carrinho para um pedido
	
	private int quantItens;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemPedido> itemPedidos;
	
	private String data;
	private double valortotal;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	
	public void add(ItemPedido itemPedido) {
		this.itemPedidos.add(itemPedido);
		this.quantItens += itemPedido.getQuantidade();
		this.valortotal += itemPedido.getValorTotal();
	}
	
	public void remove(ItemPedido itemPedido) {
		this.quantItens -= itemPedido.getQuantidade();
		this.valortotal -= itemPedido.getValorTotal();
		this.itemPedidos.remove(itemPedido);
	}
	
	public void remove(Long id) {
		ItemPedido itemPedido = null;
		
		for(int i = 0; i< itemPedidos.size(); i++) {
			if(itemPedidos.get(i).getId() == id) {
				itemPedido = itemPedidos.get(i);
			}
			if(itemPedido!=null) {
				remove(itemPedido);
				this.quantItens -= itemPedido.getQuantidade();
				this.valortotal -= itemPedido.getValorTotal();
			}
			itemPedido = null;
		}
	}
   
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public int getQuantItens() {
		return quantItens;
	}
	public void setQuantItens(int quantItens) {
		this.quantItens = quantItens;
	}
	
	public List<ItemPedido> getItens(){
		return this.itemPedidos;
	}
	
	
}
