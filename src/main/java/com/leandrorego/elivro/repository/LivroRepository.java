package com.leandrorego.elivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leandrorego.elivro.models.Livro;

public interface LivroRepository extends JpaRepository<Livro,Long> {
   public Livro findById(long id);
 
}
