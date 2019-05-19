package com.leandrorego.elivro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrorego.elivro.model.Editora;
import com.leandrorego.elivro.repository.EditoraRepository;

@Service
public class EditoraService {
	
	@Autowired
	private EditoraRepository repository;
	
	public void save(Editora editora) {
        repository.saveAndFlush(editora);
    }

	public List<Editora> listaAll(){
		return repository.findAll();
	}
	
	public Editora findOne(Long id) {
        return repository.getOne(id);
    }
     
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
