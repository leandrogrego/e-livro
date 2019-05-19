package com.leandrorego.elivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.model.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> { }
