package com.leandrorego.elivro.controller;

import java.util.Date;
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

import com.leandrorego.elivro.model.Frete;
import com.leandrorego.elivro.model.ItemPedido;
import com.leandrorego.elivro.model.Livro;
import com.leandrorego.elivro.model.Pedido;
import com.leandrorego.elivro.model.Usuario;
import com.leandrorego.elivro.service.FreteService;
import com.leandrorego.elivro.service.ItemPedidoService;
import com.leandrorego.elivro.service.LivroService;
import com.leandrorego.elivro.service.PedidoService;
import com.leandrorego.elivro.service.UsuarioService;



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
	
	@Autowired
	private LivroService servicelivro;
	
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
		Frete frete = ipedido.getFrete();
		
		pedido.setValorTotal(ipedido.getValorTotal());
		pedido.setData(new Date());
		pedido.setUsuario(serviceusuario.findOne((long) 172));
		
		service.save(pedido);
		
		frete.setPedido(pedido);
		servicefrete.save(frete);
		
		
		
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
	
	@GetMapping("/listarporusuario")
	public ModelAndView findByUsuario() {
		Usuario u = serviceusuario.findOne((long) 172);
		
		List<Pedido> pedidos = service.findByUsuario(u);
		//List<ItemPedido> itempedidos = serviceitempedido.findByListPedido(pedidos);
		/*List<Livro> livros = servicelivro.findByPedido((long) 99);
		
		for(Livro l : livros){
			System.out.println(" AQUIIIIIIII ----->>>>> "+ l.getTitulo());
		}*/
		
		ModelAndView mv = new ModelAndView("pedido/listar");
		mv.addObject("pedidos", pedidos);
		//mv.addObject("itempedidos", itempedidos);
		
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
	
	@GetMapping("/detailLivroPedido/{id}")
	public ModelAndView detailLivroByPedido(@PathVariable("id") Long id) {
		
		List<Livro> livros = servicelivro.findByPedido(id);
		
		/*for(Livro l : livros){
			System.out.println(" AQUIIIIIIII ----->>>>> "+ l.getTitulo());
		}*/
			
		ModelAndView mv = new ModelAndView("livro/listarporpedido");
		mv.addObject("livros", livros);
		
		return mv;
	}
	
	@GetMapping("/detailsByPedido/{id}")
	public ModelAndView detailsByPedido(@PathVariable("id") Long id) {
		
		Pedido p = service.findOne(id);
		
		ModelAndView mv = new ModelAndView("frete/detailsByPedido");
		mv.addObject("frete", servicefrete.findByPedido(p));
		
		return mv;
	}

}
