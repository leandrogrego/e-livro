package com.leandrorego.elivro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leandrorego.elivro.service.AutorService;
import com.leandrorego.elivro.service.GeneroService;
import com.leandrorego.elivro.service.LivroService;
import com.leandrorego.elivro.service.UsuarioService;

@Controller
@RequestMapping("")
public class HomeController {

	@Autowired
	private LivroService livroCrud;

	@Autowired
	private UsuarioService userCrud;

	@Autowired
	private AutorService autorCrud;
	
	@Autowired
	private GeneroService generoCrud;

	@GetMapping("/home")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home/home");
		mv.addObject("livros", livroCrud.findAll());
		mv.addObject("autores", autorCrud.findAll());
		mv.addObject("generos", generoCrud.findAll());
		return mv;
	}
	
	@GetMapping("/")
	public ModelAndView index() {
		return home();
	}
}
