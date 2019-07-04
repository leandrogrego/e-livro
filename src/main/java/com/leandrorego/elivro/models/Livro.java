package com.leandrorego.elivro.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Livro implements Serializable {
	
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

	@NotBlank (message = "titulo obrigarotio")
	private String titulo;
	
	@Lob
	private byte[] foto;
	
	// Muitos Livros para uma genero
	@ManyToOne(cascade = CascadeType.ALL)
	private Genero genero;

	// Muitos livros para muitos autores
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Autor> autores;

	// Muitos livros para uma editora
	@ManyToOne(cascade = CascadeType.ALL)
	private Editora editora;
	
	private String sinopse;
	private double isbn;
	private int edicao;
	private String peso;
	private String ano;
	private String valor;

	public long getId() {
		return id;
	}

	public String getValor() {
		return valor;
	}

	public void setId(long id) {
		this.id = id;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public Editora getEditora() {
		return editora;
	}

	public void setEditora(Editora editora) {
		this.editora = editora;
	}

	public Genero getCategoria() {
		return genero;
	}

	public void setCategoria(Genero genero) {
		this.genero = genero;
	}

	public List<Autor> getAutores() {
		return autores;
	}

	public void setAutores(List<Autor> autores) {
		this.autores = autores;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public double getIsbn() {
		return isbn;
	}

	public void setIsbn(double isbn) {
		this.isbn = isbn;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getEdicao() {
		return edicao;
	}

	public void setEdicao(int edicao) {
		this.edicao = edicao;
	}

	public String getCategoriaNome() {
		return genero.getNome();
	}

	public String getSinopse() {
		return sinopse;
	}

	public void setSinopse(String sinopse) {
		this.sinopse = sinopse;
	}

	public String getAno() {
		return ano;
	}

	public void setAno(String ano) {
		this.ano = ano;
	}

	@Override
	public String toString() {
		return String.valueOf(this.id);
	}
}
