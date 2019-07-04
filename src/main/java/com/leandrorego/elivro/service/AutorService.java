package com.leandrorego.elivro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrorego.elivro.models.Autor;
import com.leandrorego.elivro.repository.AutorRepository;

@Service
public class AutorService {
	@Autowired
	private AutorRepository crud;
   
	public boolean create(Autor autor){
		crud.saveAndFlush(autor);
		return true;
	}
	
    
    public List<Autor> findAll() {
		return crud.findAll();
	}
	
	public Autor update(long id){
		return crud.getOne(id);
	}
	
	public boolean delete (Autor autor){
		   crud.delete(autor);
		   return true;
   	} 
	
   	public long count(){
	   return crud.count();
   	}

   	public Autor findByid(long id) {
		return crud.findById(id);
	}

}