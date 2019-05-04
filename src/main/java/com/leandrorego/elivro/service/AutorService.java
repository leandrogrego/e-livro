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

	public List<Autor> listaAll(){
		return repository.findAll();
	}
	
	public void cadastrar(Autor aut) {
		repository.saveAndFlush(aut);
	}
	
	public Autor  findOne(Long id) {
        return repository.getOne(id);
    }
     
    public Autor save(Autor aut) {
        return repository.saveAndFlush(aut);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }

}