package com.leandrorego.elivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.models.Usuario;
@Repository
public interface UserRepository extends JpaRepository<Usuario,Long>{
    public Usuario findByusername(String username);
    public Usuario findById(long id);
}
