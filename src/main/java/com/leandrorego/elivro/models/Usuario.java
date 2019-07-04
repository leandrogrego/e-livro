package com.leandrorego.elivro.models;

import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Matheus Pereira Classe resposavel por criar o usuário e implementar a
 *         classe UserDetails para que o spring security possa salvar esse
 *         usuário com as devidas autenticações
 */

@Entity
public class Usuario extends Pessoa implements UserDetails {

	private static final long serialVersionUID = 1L;

	/**
	 * Muitos usuários para um endereço
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	/**
	 * Um usuário para muitos pedidos
	 */
	@OneToMany(cascade = CascadeType.ALL)
	private List<Pedido> pedidos;

	/**
	 * Um usuário para um carrinho
	 */
	@OneToOne(cascade = CascadeType.ALL)
	private Carrinho carrinho;

	/**
	 * Muitos usuários para muitas permições
	 */
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Role> roles;

	/**
	 * Status ativo ou inativo
	 */
	@Enumerated(EnumType.STRING)
	private Status status;

	@Lob
	private byte[] foto;

	@NotBlank(message = "O nome de login é obrigatório")
	@Size(min = 4, message = "USERNAME tem que ter pelo menos 4 letras")
	@Column(unique = true)
	private String username;

	@NotNull
	@NotEmpty(message = "Password não pode ser vazio.")
	@Size(min = 6, message = "Password deve ser no mínimo 6 caracter.")
	private String password;

	public Usuario(String nome, Endereco endereco, List<Pedido> pedidos,
			Carrinho carrinho, List<Role> roles, Status status, byte[] foto,
			String username, String password) {

		this.setNome(nome);
		this.endereco = endereco;
		this.pedidos = pedidos;
		this.carrinho = carrinho;
		this.roles = roles;
		this.status = status;
		if(foto!=null) this.foto = foto;
		this.username = username;
		this.password = password;
	}
	
	public Usuario() {

	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.username;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.roles;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
