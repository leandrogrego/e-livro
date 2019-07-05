package com.leandrorego.elivro.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import com.leandrorego.elivro.models.Genero;
import com.leandrorego.elivro.models.Autor;
import com.leandrorego.elivro.models.Editora;
import com.leandrorego.elivro.models.Livro;
import com.leandrorego.elivro.models.Usuario;
import com.leandrorego.elivro.service.AutorService;
import com.leandrorego.elivro.service.EditoraService;
import com.leandrorego.elivro.service.GeneroService;
import com.leandrorego.elivro.service.LivroService;

@Controller
@RequestMapping("/livro")
public class LivroController {
	
	@Autowired
	LivroService crud;
	
	@Autowired
	AutorService crudAutor;
	
	@Autowired
	EditoraService crudEditora;
	
	@Autowired
	GeneroService crudGenero;

	@GetMapping(value = "/cadastro")
	public ModelAndView register(Livro livro, Genero genero, Editora editora) {
		livro.setAutores(new ArrayList<>());
		livro.setCategoria(genero);
		livro.setEditora(editora);
		
		List<Autor> autores = crudAutor.findAll();
		List<Editora> editoras = crudEditora.findAll();
		List<Genero> generos = crudGenero.findAll();
		
		ModelAndView mv = new ModelAndView("cadastro/livro");
		mv.addObject("livro",livro);
		mv.addObject("autores", autores);
		mv.addObject("editoras",editoras);
		mv.addObject("generos", generos);
		return mv;
	}

	@PostMapping(value = "/salvar")
	public String create(@Valid Livro livro,@RequestParam("uploadfile") MultipartFile file, BindingResult result) throws IOException {
			
		if (result.hasErrors()) {
			return "redirect:/livro/cadastro";
		}
		
		if (!file.isEmpty()) {
			livro.setFoto(file.getBytes());
		}
		
		if (livro.getAutores() != null) {
			for (int i = 0; i < livro.getAutores().size(); i++) {
				if (livro.getAutores().get(i).getNome() == null || livro.getAutores().get(i).getNome().equals("")) {
					crudAutor.delete(livro.getAutores().get(i));
					livro.getAutores().remove(i);
				}
			}
		}
		
		crud.create(livro);
		return "redirect:/livro/lista";
	}

	@GetMapping(value = "/lista")
	public ModelAndView readAll() {
		ModelAndView mv = new ModelAndView("lista/livros");
		mv.addObject("livros",crud.findAll());
		return mv;
	}

	@GetMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("cadastro/livro");
		Livro livro = crud.update(id);
		List<Autor> autores = crudAutor.findAll();
		List<Editora> editoras = crudEditora.findAll();
		List<Genero> generos = crudGenero.findAll();
		
		System.out.println("Passou");
		mv.addObject("livro",livro);
		mv.addObject("autores", autores);
		mv.addObject("editoras",editoras);
		mv.addObject("generos", generos);
		return mv;
	}
	
	@GetMapping("livro/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		crud.delete(crud.findByid(id));
		return "redirect:/lbook/livros";
	}
	
	@RequestMapping(value = "/imagem/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImagem(@PathVariable("id") Long id) throws IOException {

		Livro livro = crud.findByid(id);
		byte[] imagem = livro.getFoto();
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imagem, headers, HttpStatus.OK);
	}
}
