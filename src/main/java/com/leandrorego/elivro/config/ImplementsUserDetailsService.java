package com.leandrorego.elivro.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.models.Usuario;
import com.leandrorego.elivro.repository.UserRepository;

/**
 * Classe de serviço do spring security onde verifica no banco os dados do
 * usuario.
 */
@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository crud;

	@Override
	public UserDetails loadUserByUsername(String username)
	throws UsernameNotFoundException {
		
		Usuario usuario = crud.findByusername(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado!");
		}
		
		return new User(usuario.getUsername(),usuario.getPassword(),true,true,true,true,usuario.getAuthorities());
	}
}
