package com.leandrorego.elivro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrorego.elivro.models.Genero;
import com.leandrorego.elivro.repository.GeneroRepository;

@Service
public class GeneroService {
	@Autowired
	private GeneroRepository crud;
   
   	public long count(){
	   return crud.count();
   	}
   
	public Genero findByid(long id){
		return crud.getOne(id);
	}
   
	public boolean create(Genero genero){
		crud.saveAndFlush(genero);
		return true;
	}
   
	public boolean delete (Genero genero){
	   crud.delete(genero);
	   return true;
   	} 
   
	public Genero update(long id){
		return crud.getOne(id);
	}
   
   public Genero findById(long id){
	   return crud.findById(id);
   }
   
	public List<Genero> findAll(){
		return crud.findAll();
	}
}
