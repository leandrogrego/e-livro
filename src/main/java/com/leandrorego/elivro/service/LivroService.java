package com.leandrorego.elivro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.leandrorego.elivro.models.Livro;
import com.leandrorego.elivro.repository.LivroRepository;

@Service
public class LivroService {
	
	@Autowired
	private LivroRepository crud;
	
	public boolean create(Livro livro){
		crud.saveAndFlush(livro);
		return true;
	}
	
	public Livro findByid(long id){
		return crud.getOne(id);
	}
	
	public List<Livro> findAll(){
		return crud.findAll();
	}
	
	public Livro update(long id){
		return crud.getOne(id);
	}
	
	public boolean delete(Livro livro){
		crud.delete(livro);
		return true;
	}
	
	public long count(){
		return crud.count();
	}
}
