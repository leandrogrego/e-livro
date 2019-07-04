package com.leandrorego.elivro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.models.ItemPedido;
@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido,Long> {

}
