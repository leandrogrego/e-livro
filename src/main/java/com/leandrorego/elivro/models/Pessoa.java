package com.leandrorego.elivro.models;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

/**
 * @author Matheus Pinheiro
 * Classe responsavel por fornecer os dados dos autores e usuários
 */

@MappedSuperclass
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * Para usar o banco no Heroku troque para essa notação
	 * 
	 * @GeneratedValue(generator ="increment")
	 * @GenericGenerator(name="increment",strategy ="increment")
	 * */

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank( message = "O nome do usuário é obrigatorio")
	private String nome;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
}
