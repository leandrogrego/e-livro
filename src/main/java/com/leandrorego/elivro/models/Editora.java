package com.leandrorego.elivro.models;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Editora implements Serializable {
    
	private static final long serialVersionUID = 1L;

	/**
	 * Para Usar o banco no Heroku 
	 *@GeneratedValue(generator ="increment")
	 *@GenericGenerator(name="increment",strategy ="increment")
	*/
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@NotBlank (message ="O nome da Editora é obrigatorio")
    private String nome;
	
	@ManyToMany(mappedBy = "autores")
	private List<Livro> livros;
	
    
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

	@Override
	public String toString() {
		return nome;
	}
	
	public List<Livro> getLivros(){
		return livros;
	}
}
