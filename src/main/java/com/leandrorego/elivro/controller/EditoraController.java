package com.leandrorego.elivro.controller;

import java.io.IOException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.leandrorego.elivro.models.Autor;
import com.leandrorego.elivro.models.Editora;
import com.leandrorego.elivro.service.AutorService;
import com.leandrorego.elivro.service.EditoraService;
import com.leandrorego.elivro.service.LivroService;

@Controller
@RequestMapping("/editora")
public class EditoraController {
	
	@Autowired
	EditoraService crud;
	
	@Autowired
	LivroService crudLivro;

	@GetMapping(value = "/cadastro")
	public ModelAndView register(Editora editora) {
		
		ModelAndView mv = new ModelAndView("cadastro/editora");
		mv.addObject("editora",editora);
		return mv;
	}

	@PostMapping(value = "/salvar")
	public String create(@Valid Editora editora, BindingResult result) throws IOException {
			
		if (result.hasErrors()) {
			return "redirect:/editora/cadastro";
		}
		
		crud.create(editora);
		return "redirect:/editora/lista";
	}

	@GetMapping(value = "/lista")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("lista/editoras");
		mv.addObject("editoras",crud.findAll());
		return mv;
	}

	@GetMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("cadastro/editora");
		Editora editora = crud.update(id);
		mv.addObject("editora",editora);
		return mv;
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		crud.delete(crud.findByid(id));
		return "redirect:/editora/lista";
	}
	
}
