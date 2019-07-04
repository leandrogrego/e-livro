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

@Entity
public class Pedido implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Para uso do banco no Hiroku
	 * 
	 * @GeneratedValue(generator ="increment")
	 * @GenericGenerator(name="increment",strategy ="increment")
	 */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String data;
	private double valortotal;
	
	@Enumerated(EnumType.STRING)
	private Status status;
	
	//um pedido para muitos itens
	@OneToMany(cascade = CascadeType.ALL)
	private List<ItemPedido> itemPedidos;
   
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<ItemPedido> getItemPedidos() {
		return itemPedidos;
	}

	public void setItemPedidos(List<ItemPedido> itemPedidos) {
		this.itemPedidos = itemPedidos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public double getValortotal() {
		return valortotal;
	}

	public void setValortotal(double valortotal) {
		this.valortotal = valortotal;
	}
}
