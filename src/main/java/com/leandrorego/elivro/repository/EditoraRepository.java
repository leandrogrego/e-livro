package com.leandrorego.elivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.leandrorego.elivro.models.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora,Long> {
    public Editora findById(long id);
}
