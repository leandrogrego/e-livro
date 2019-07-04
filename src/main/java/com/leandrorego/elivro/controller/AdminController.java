package com.leandrorego.elivro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leandrorego.elivro.service.AutorService;
import com.leandrorego.elivro.service.EditoraService;
import com.leandrorego.elivro.service.GeneroService;
import com.leandrorego.elivro.service.LivroService;
import com.leandrorego.elivro.service.UsuarioService;

@Controller
@RequestMapping("")
public class AdminController {

	@Autowired
	private LivroService livroCrud;

	@Autowired
	private UsuarioService userCrud;

	@Autowired
	private AutorService autorCrud;

	@Autowired
	private GeneroService generoCrud;

	@Autowired
	private EditoraService editoraCrud;

	@GetMapping("/admin")
	public ModelAndView home() {
		ModelAndView mv = new ModelAndView("home/admin");
		mv.addObject("countLivro", livroCrud.count());
		mv.addObject("countCliente", userCrud.count());
		mv.addObject("countAutor", autorCrud.count());
		mv.addObject("countGenero", generoCrud.count());
		mv.addObject("countEditora", editoraCrud.count());
		return mv;
	}
}