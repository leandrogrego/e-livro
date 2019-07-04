package com.leandrorego.elivro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrorego.elivro.models.Usuario;
import com.leandrorego.elivro.repository.UserRepository;


@Service
public class UsuarioService {
	
	@Autowired
	private UserRepository crud;

	public Usuario findByusername(String username) {
		return crud.findByusername(username);
	}

	public boolean create(Usuario usuario) {
		crud.saveAndFlush(usuario);
		return true;
	}

	public Usuario findById(long id) {
		return crud.findById(id);
	}

	public List<Usuario> findAll() {
		return crud.findAll();
	}

	public Usuario update(long id) {
		return crud.getOne(id);
	}
	
		
	public boolean delete(Usuario usuario) {
		crud.delete(usuario);
		return true;
	}

	public long count() {
		return crud.count();
	}
}
