package com.leandrorego.elivro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrorego.elivro.model.Usuario;
import com.leandrorego.elivro.repository.UsuarioRepository;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;


@Service
public class UsuarioService implements UserDetailsService{
	
	@Autowired
	private UsuarioRepository repository;
	
	public Usuario save(Usuario usuario) {
		return repository.saveAndFlush(usuario);
	}

	public List<Usuario> findAll() {
		return repository.findAll();
	}
	
	public Usuario findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	/*
	public Usuario findEmail(String email ) {
		return repository.findByEmail(email);
	}*/
        
            public Usuario findByEmail(String email) {
            Usuario usuario = new Usuario();                         
            if(email!=null){
                usuario.setEmail(email);                          
                Example<Usuario> example;
                example = Example.of(usuario);
                usuario = repository.findOne(example).get();
            } 
            return usuario;
        }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                Usuario usuario = findByEmail(email);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não Encontrado!");
        }
        return usuario;
    }

}
