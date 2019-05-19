package com.leandrorego.elivro.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.leandrorego.elivro.model.Pedido;
import com.leandrorego.elivro.model.Usuario;
import com.leandrorego.elivro.repository.PedidoRepository;

@Repository
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido save(Pedido pedido) {
		return repository.saveAndFlush(pedido);
	}

	public List<Pedido> findAll() {
		return repository.findAll();
	}
	
	public Pedido findOne(Long id) {
		return repository.getOne(id);
	}
	
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	public List<Pedido> findByUsuario(Usuario u) {
		return repository.findByUsuario(u);
	}

}
