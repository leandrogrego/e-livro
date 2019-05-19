package com.leandrorego.elivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{ 
	
		//public Usuario findByEmail(String email);
	
}
