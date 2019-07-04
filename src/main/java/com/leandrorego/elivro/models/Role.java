package com.leandrorego.elivro.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import org.springframework.security.core.GrantedAuthority;

/**
 * 
 * @author Matheus Pinheiro
 *  Classe responsavel pelas permições do usuario
 */
@Entity
public class Role implements GrantedAuthority {

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

	@Column(unique = true)
	private String nome;

	// Muitas funções para muitos usuários
	@ManyToMany(mappedBy = "roles")
	private List<Usuario> usuarios;

	@Override
	public String getAuthority() {
		return this.nome;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
