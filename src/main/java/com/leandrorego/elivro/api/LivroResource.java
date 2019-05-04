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

import com.leandrorego.elivro.model.Livro;
import com.leandrorego.elivro.service.LivroService;

@RestController
@RequestMapping("/api/livro")
public class LivroResource {

	@Autowired
	private LivroService service;

	/**
	 * Método para realização do cadastro das informações via rest
	 * 
	 * @param liv
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Livro> adicionar(@RequestBody @Valid Livro liv) {

		if (liv != null) {
			service.save(liv);
			return new ResponseEntity<Livro>(liv, HttpStatus.CREATED);
		}
		return new ResponseEntity<Livro>(liv, HttpStatus.CONFLICT);

	}

	@GetMapping("/listar")
	public List<Livro> listar() {
		return service.listaAll();
	}

	/**
	 * Retorna o objeto que foi encontrado a partir do ID
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Livro> buscar(@PathVariable Long id) {
		Livro l = service.findOne(id);

		// Retorna 404
		if (c == null) {
			return ResponseEntity.notFound().build();
		}

		// Retornando o objeto que foi encontrado
		return ResponseEntity.ok(c);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Categoria categoriaAlterada) {

		Livro existente = service.findOne(id);

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
		Livro liv = service.findOne(id);

		// Retorna 404
		if (liv == null) {
			return ResponseEntity.notFound().build();
		}
		
		//apagando
		service.delete(id);
			
		// Retorno de sucesso, porém sem conteúdo.
		return ResponseEntity.noContent().build();

	}

}