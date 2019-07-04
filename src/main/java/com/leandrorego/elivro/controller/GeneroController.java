package com.leandrorego.elivro.controller;

import java.io.IOException;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.leandrorego.elivro.models.Genero;
import com.leandrorego.elivro.service.GeneroService;
import com.leandrorego.elivro.service.LivroService;

@Controller
@RequestMapping("/genero")
public class GeneroController {
	
	@Autowired
	GeneroService crud;
	
	@Autowired
	LivroService crudLivro;

	@GetMapping(value = "/cadastro")
	public ModelAndView register(Genero genero) {
		
		ModelAndView mv = new ModelAndView("cadastro/genero");
		mv.addObject("genero",genero);
		return mv;
	}

	@PostMapping(value = "/salvar")
	public String create(@Valid Genero genero, BindingResult result) throws IOException {
			
		if (result.hasErrors()) {
			return "redirect:/genero/cadastro";
		}
		
		crud.create(genero);
		return "redirect:/genero/lista";
	}

	@GetMapping(value = "/lista")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("lista/generos");
		mv.addObject("generos",crud.findAll());
		return mv;
	}

	@GetMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("cadastro/genero");
		Genero genero = crud.update(id);
		mv.addObject("genero",genero);
		return mv;
	}
	
	@GetMapping("genero/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		crud.delete(crud.findByid(id));
		return "redirect:/genero/lista";
	}
	
}
