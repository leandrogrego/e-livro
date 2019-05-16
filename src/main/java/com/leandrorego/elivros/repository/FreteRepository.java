package com.leandrorego.elivros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivros.model.Frete;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long>{ }
