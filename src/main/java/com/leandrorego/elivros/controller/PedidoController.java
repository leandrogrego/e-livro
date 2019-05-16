package com.leandrorego.elivros.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leandrorego.elivros.model.Frete;
import com.leandrorego.elivros.model.ItemPedido;
import com.leandrorego.elivros.model.Pedido;
import com.leandrorego.elivros.service.FreteService;
import com.leandrorego.elivros.service.ItemPedidoService;
import com.leandrorego.elivros.service.PedidoService;
import com.leandrorego.elivros.service.UsuarioService;



@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService service;
	
	@Autowired
	private ItemPedidoService  serviceitempedido;
	
	@Autowired
	private FreteService servicefrete;
	
	@Autowired
	private UsuarioService serviceusuario;
	
	@GetMapping("/add")
	public ModelAndView add(Pedido pedido) {
		
		ModelAndView mv = new ModelAndView("/pedido/form");
		mv.addObject("pedido", pedido);
		
		return mv;
	}
	
	@GetMapping("/addPedido/{id}")
	public ModelAndView addPedido(@PathVariable("id") Long id) {
		
		Pedido pedido = new Pedido();
		
		service.save(pedido);
		
		ItemPedido ipedido = serviceitempedido.findOne(id);
		
		ipedido.setPedido(pedido);
		
		serviceitempedido.save(ipedido);
		
		pedido.setValorTotal(ipedido.getValorTotal());
		pedido.setData(new Date());
		pedido.setUsuario(serviceusuario.findOne((long) 1));
		
		service.save(pedido);
		
		Frete frete = new Frete();
		servicefrete.save(frete);
		frete.setPedido(pedido);
	
		
		ModelAndView mv = new ModelAndView("/frete/form");
		mv.addObject("pedido", pedido);
		mv.addObject("frete", frete);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Pedido pedido, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(pedido);
		}
		
		service.save(pedido);
		
		return findAll();
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("pedido/listar");
		mv.addObject("pedidos", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}

}
