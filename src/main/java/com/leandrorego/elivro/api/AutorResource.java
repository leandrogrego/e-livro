package com.leandrorego.elivro.api;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leandrorego.elivro.model.Autor;
import com.leandrorego.elivro.service.AutorService;

@RestController
@RequestMapping("/api/autor")
public class AutorResource {

	@Autowired
	private AutorService service;

	/**
	 * Método para realização do cadastro das informações via rest
	 * 
	 * @param aut
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Autor> adicionar(@RequestBody @Valid Autor aut) {

		if (aut != null) {
			service.save(aut);
			return new ResponseEntity<Autor>(aut, HttpStatus.CREATED);
		}
		return new ResponseEntity<Autor>(aut, HttpStatus.CONFLICT);

	}

	@GetMapping("/listar")
	public List<Autor> listar() {
		return service.listaAll();
	}

	/**
	 * Retorna o objeto que foi encontrado a partir do ID
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Autor> buscar(@PathVariable Long id) {
		Autor a = service.findOne(id);

		// Retorna 404
		if (a == null) {
			return ResponseEntity.notFound().build();
		}

		// Retornando o objeto que foi encontrado
		return ResponseEntity.ok(a);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Autor> atualizar(@PathVariable Long id, @RequestBody Autor categoriaAlterada) {

		Autor existente = service.findOne(id);

		// Retorna 404
		if (existente == null) {
			return ResponseEntity.notFound().build();
		}

		// Esse método realiza a cópia dos valores da propriedade ignorando o id.
		BeanUtils.copyProperties(categoriaAlterada, existente, "id");

		// O save tanto salva como altera.
		existente = service.save(existente);

		// Retornando o objeto que foi encontrado
		return ResponseEntity.ok(existente);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long id) {
		Autor aut = service.findOne(id);

		// Retorna 404
		if (aut == null) {
			return ResponseEntity.notFound().build();
		}
		
		//apagando
		service.delete(id);
			
		// Retorno de sucesso, porém sem conteúdo.
		return ResponseEntity.noContent().build();

	}

}
