package com.leandrorego.elivro.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leandrorego.elivro.model.Livro;
import com.leandrorego.elivro.service.AutorService;
import com.leandrorego.elivro.service.CategoriaService;
import com.leandrorego.elivro.service.EditoraService;
import com.leandrorego.elivro.service.LivroService;


@Controller
@RequestMapping("/livro")
public class LivroController {
	
	@Autowired
	private LivroService service;
	
	@Autowired
	private AutorService serviceautor;

	@Autowired
	private CategoriaService servicecategoria;
	
	@Autowired
	private EditoraService serviceeditora;
	
	@GetMapping("/add")
	public ModelAndView add(Livro livro) {
		
		ModelAndView mv = new ModelAndView("/livro/form");
		mv.addObject("autores", serviceautor.findAll());
		mv.addObject("categorias", servicecategoria.listaAll());
		mv.addObject("editoras", serviceeditora.listaAll());
		mv.addObject("livro", livro);
		
		return mv;
	}
	
	@PostMapping("/save")
	public ModelAndView save(@Valid Livro livro, BindingResult result) {
		
		if(result.hasErrors()) {
			return add(livro);
		}
		
		service.save(livro);
		
		return findAll();
	}
	
	@GetMapping("/listar")
	public ModelAndView findAll() {
		
		ModelAndView mv = new ModelAndView("livro/listar");
		mv.addObject("livros", service.findAll());
		
		return mv;
	}
	
	@GetMapping("/edit/{id}")
	public ModelAndView edit(@PathVariable("id") Long id) {
		
		return add(service.findOne(id));
	}
	
	@GetMapping("/details/{id}")
	public ModelAndView details(@PathVariable("id") Long id) {
		
		ModelAndView mv = new ModelAndView("livro/detalhes");
		mv.addObject("livro", service.findOne(id));
		
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return findAll();
	}

}
