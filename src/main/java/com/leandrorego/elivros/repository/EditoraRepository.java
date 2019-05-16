package com.leandrorego.elivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivros.model.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> { }
