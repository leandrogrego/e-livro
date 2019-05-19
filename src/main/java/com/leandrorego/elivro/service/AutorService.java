package com.leandrorego.elivro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrorego.elivro.model.Autor;
import com.leandrorego.elivro.repository.AutorRepository;


@Service
public class AutorService {

	@Autowired
	private AutorRepository repository;
	
	public Autor save(Autor autor) {
		return repository.saveAndFlush(autor);
	}

	public List<Autor> findAll() {
		return repository.findAll();
	}
	
	public Autor findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
}
