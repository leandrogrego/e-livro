package com.leandrorego.elivro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.model.Autor;
import com.leandrorego.elivro.model.Categoria;
import com.leandrorego.elivro.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long>{

	public List<Autor> findByNomeLike(String titulo);
	
	@Query("select c from Livro c where c.titulo = ?1")
	public List<Autor> findByName(String titulo);
}
