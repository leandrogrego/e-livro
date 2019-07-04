package com.leandrorego.elivro.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * @author Matheus Pinheiro
 * Classe responsavel por habilita um carrinho de compras
 * para o usuário comprar os livros que deseja.
 */

@Entity
public class Carrinho implements Serializable {
	
	private static final long serialVersionUID = 1L;

	/**
	 * Para usar o banco no Heroku
	 * @GeneratedValue(generator ="increment")
	 * @GenericGenerator(name="increment",strategy ="increment")
	 * */
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	//um carrinho para um pedido
	
	@OneToOne(cascade = CascadeType.ALL)
	private Pedido pedido;
	
	private int quantItens;
	
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
	public Pedido getPedido() {
		return pedido;
	}
	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
}
