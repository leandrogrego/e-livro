package com.leandrorego.elivro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrorego.elivro.model.Livro;
import com.leandrorego.elivro.repository.LivroRepository;


@Service
public class LivroService {
	
	@Autowired
	private LivroRepository repository;

	public Livro save(Livro livro) {
		return repository.saveAndFlush(livro);
	}

	public List<Livro> findAll() {
		return repository.findAll();
	}
	
	public Livro findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Livro> findByPedido(Long id) {
		return repository.findByPedido(id);
	}

}
