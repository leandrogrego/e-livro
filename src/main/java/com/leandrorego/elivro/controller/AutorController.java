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
import com.leandrorego.elivro.service.AutorService;
import com.leandrorego.elivro.service.EditoraService;
import com.leandrorego.elivro.service.LivroService;

@Controller
@RequestMapping("/autor")
public class AutorController {
	
	@Autowired
	AutorService crud;
	
	@Autowired
	EditoraService crudEditora;

	@Autowired
	LivroService crudLivro;

	
	@GetMapping(value = "/cadastro")
	public ModelAndView register(Autor autor) {
		
		ModelAndView mv = new ModelAndView("cadastro/autor");
		mv.addObject("autor",autor);
		return mv;
	}

	@PostMapping(value = "/salvar")
	public String create(@Valid Autor autor,@RequestParam("uploadfile") MultipartFile file, BindingResult result) throws IOException {
			
		if (result.hasErrors()) {
		}
		
		if (!file.isEmpty()) {
			autor.setFoto(file.getBytes());
		}
		
		if (autor.getLivros() != null) {
			for (int i = 0; i < autor.getLivros().size(); i++) {
				if (autor.getLivros().get(i).getTitulo() == null || autor.getLivros().get(i).getTitulo().equals("")) {
					crudLivro.delete(autor.getLivros().get(i));
					autor.getLivros().remove(i);
				}
			}
		}
		
		crud.create(autor);
		return "redirect:/autor/lista";
	}

	@GetMapping(value = "/lista")
	public ModelAndView findAll() {
		ModelAndView mv = new ModelAndView("lista/autores");
		mv.addObject("autores",crud.findAll());
		return mv;
	}

	@GetMapping("/update/{id}")
	public ModelAndView update(@PathVariable("id") long id) {
		ModelAndView mv = new ModelAndView("cadastro/autor");
		Autor autor = crud.update(id);
		mv.addObject("autor",autor);
		return mv;
	}
	
	@GetMapping("autor/delete/{id}")
	public String delete(@PathVariable("id") long id) {
		crud.delete(crud.findByid(id));
		return "redirect:/autores";
	}
	
	@RequestMapping(value = "/imagem/{id}", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImagem(@PathVariable("id") Long id) throws IOException {

		Autor autor = crud.findByid(id);
		byte[] imagem = autor.getFoto();
		
		
		final HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.IMAGE_PNG);
		return new ResponseEntity<byte[]>(imagem, headers, HttpStatus.OK);
	}
}
