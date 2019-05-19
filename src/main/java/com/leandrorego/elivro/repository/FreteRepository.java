package com.leandrorego.elivro.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.model.Frete;
import com.leandrorego.elivro.model.Pedido;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Long>{ 
	
	@Query("select f from Frete f where f.pedido = ?1")
	public Frete findByPedido(Pedido p);
}
