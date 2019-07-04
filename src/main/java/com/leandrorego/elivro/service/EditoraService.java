package com.leandrorego.elivro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrorego.elivro.models.Editora;
import com.leandrorego.elivro.models.Livro;
import com.leandrorego.elivro.repository.EditoraRepository;

@Service
public class EditoraService {
	@Autowired
	private EditoraRepository crud;
   
	public boolean create(Editora editora){
		crud.saveAndFlush(editora);
		return true;
	}
	
    
    public List<Editora> findAll() {
		return crud.findAll();
	}
	
	public Editora update(long id){
		return crud.getOne(id);
	}
	
	public boolean delete (Editora editora){
		   crud.delete(editora);
		   return true;
   	} 
	
   	public long count(){
	   return crud.count();
   	}

   	public Editora findByid(long id) {
		return crud.findById(id);
	}
   	
}