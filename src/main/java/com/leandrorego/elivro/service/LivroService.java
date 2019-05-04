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

	public List<Livro> listaAll(){
		return repository.findAll();
	}
	
	public void cadastrar(Livro liv) {
		repository.saveAndFlush(liv);
	}
	
	public Livro  findOne(Long id) {
        return repository.getOne(id);
    }
     
    public Livro save(Livro liv) {
        return repository.saveAndFlush(liv);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }
	
}