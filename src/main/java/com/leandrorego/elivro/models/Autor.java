package com.leandrorego.elivro.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

@Entity
public class Autor extends Pessoa {

	private static final long serialVersionUID = 1L;

	@ManyToMany(mappedBy = "autores")
	private List<Livro> livros;
	
	@Lob
	private byte[] foto;
	
	public Autor(String nome, byte[] foto) {

		this.setNome(nome);
		this.foto = foto;

	}
	
	public Autor() {

	}
	

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	@Override
	public String toString() {
		return getNome();
	}
	
	public void setFoto(byte[] foto) {
		this.foto = foto;
	}
	
	public byte[] getFoto() {
		return foto;
	}
}
