package com.leandrorego.elivro.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Municipio {
	
	public Municipio() {
		// TODO Auto-generated constructor stub
	}
	
	private Long id;
	
	private String nome;
	
	private Microrregiao microrregiao;
	
	
	public Microrregiao getMicrorregiao() {
		return microrregiao;
	}

	public void setMicrorregiao(Microrregiao microrregiao) {
		this.microrregiao = microrregiao;
	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
