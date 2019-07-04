package com.leandrorego.elivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.models.Genero;

@Repository
public interface GeneroRepository extends JpaRepository<Genero,Long> {
    public Genero findById(long id);
}
