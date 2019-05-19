package com.leandrorego.elivro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.model.Pedido;
import com.leandrorego.elivro.model.Usuario;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{ 
	
	@Query("select p from Pedido p where p.usuario = ?1")
	public List<Pedido> findByUsuario(Usuario u);
	
}
