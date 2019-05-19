package com.leandrorego.elivro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.model.ItemPedido;
import com.leandrorego.elivro.model.Pedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Long> { 
	
	@Query("select ip from ItemPedido ip where ip.pedido = ?1")
	public List<ItemPedido> findByListPedido(List<Pedido> pedidos);
}
