package com.leandrorego.elivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.models.Autor;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {
    public Autor findById(long id);
}
