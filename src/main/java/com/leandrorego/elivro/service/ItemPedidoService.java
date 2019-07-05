package com.leandrorego.elivro.service;

import org.springframework.stereotype.Service;

import com.leandrorego.elivro.models.ItemPedido;
import com.leandrorego.elivro.models.Livro;
import com.leandrorego.elivro.repository.ItemPedidoRepository;

@Service
public class ItemPedidoService {
	
	private ItemPedidoRepository crud;
	
   public boolean delete (ItemPedido itemPedido){
	   crud.delete(itemPedido);
	   return true;
   }
   
   public ItemPedido findByid(long id){
		return crud.getOne(id);
   }
}
