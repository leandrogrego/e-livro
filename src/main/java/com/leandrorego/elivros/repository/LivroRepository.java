package com.leandrorego.elivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivros.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> { }
