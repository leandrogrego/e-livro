package com.leandrorego.elivros.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leandrorego.elivros.model.ItemPedido;
import com.leandrorego.elivros.model.Livro;
import com.leandrorego.elivros.service.ItemPedidoService;
import com.leandrorego.elivros.service.LivroService;
import com.leandrorego.elivros.service.UsuarioService;


@Controller
@RequestMapping("/itemPedido")
public class ItemPedidoController {
	
	@Autowired
	private ItemPedidoService service;
	
	@Autowired
	private LivroService serviceLivro;
        
        	@Autowired
	private UsuarioService serviceUsuario;
	
	@GetMapping("/add")
	public ModelAndView add(ItemPedido itemPedido) {
		
		ModelAndView mv = new ModelAndView("/itemPedido/form");
		mv.addObject("livros", serviceLivro.findAll());
		mv.addObject("itemPedido", itemPedido);
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid ItemPedido itemPedido, BindingResult result) {
		
		double valor = 0.0;
		int i = 0;
		
		if(result.hasErrors()) {
			return add(itemPedido);
		}
		
		List<Livro> livros = itemPedido.getLivro();
		for(Livro l : livros){
			valor += l.getPreco();
			i++;
		}
		
		itemPedido.setValorTotal(valor);
		itemPedido.setQuantidade(i);
		
		service.save(itemPedido);
		
		return details(itemPedido.getId());
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("itemPedido/listar");
		mv.addObject("itemPedidos", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/details/{id}")
	public ModelAndView details(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("itemPedido/details");
		mv.addObject("itemPedido", service.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}

}