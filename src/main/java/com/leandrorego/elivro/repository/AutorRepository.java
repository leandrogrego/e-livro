package com.leandrorego.elivro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.model.Autor;
import com.leandrorego.elivro.model.Categoria;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long>{

	public List<Autor> findByNomeLike(String name);
	
	@Query("select c from Autor c where c.nome = ?1")
	public List<Autor> findByName(String nome);
}
