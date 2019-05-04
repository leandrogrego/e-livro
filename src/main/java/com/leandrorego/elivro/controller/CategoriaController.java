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

import com.leandrorego.elivro.model.Categoria;
import com.leandrorego.elivro.service.CategoriaService;

@Controller
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CategoriaService categoriaService;
			
	@RequestMapping("/add")
	public ModelAndView add(Categoria cat) {
		ModelAndView mv = new ModelAndView("categoria/form");
		mv.addObject("categoria", cat);
		return mv;
	}
		
	@GetMapping("/edit/{id}")
	private ModelAndView edit( @PathVariable("id") Long id) {
		Categoria cat = categoriaService.findOne(id);
		return add(cat);
	}
	

	@GetMapping("/delete/{id}")
	private ModelAndView delete( @PathVariable("id") Long id) {
		categoriaService.delete(id);
		return findAll();
	}


	
	@PostMapping("/save")
    public ModelAndView save(@Valid Categoria cat, BindingResult result) {
		 
		if(result.hasErrors()) {
			return add(cat);
	    }

		categoriaService.cadastrar(cat);
		ModelAndView rec =findAll(); 
		return rec;
    }

	@GetMapping("/lista")
	private ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("categoria/lista");
        mv.addObject("categorias", categoriaService.listaAll());
        return mv;
	}
}















