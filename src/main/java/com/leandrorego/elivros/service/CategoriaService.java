package com.leandrorego.elivros.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrorego.elivros.model.Categoria;
import com.leandrorego.elivros.repository.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository repository;

	public void save(Categoria categoria) {
        repository.saveAndFlush(categoria);
    }
	
	public List<Categoria> listaAll(){
		return repository.findAll();
	}
	
	public Categoria  findOne(Long id) {
        return repository.getOne(id);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }


}
