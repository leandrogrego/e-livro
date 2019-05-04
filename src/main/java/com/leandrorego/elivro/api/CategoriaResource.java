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

import com.leandrorego.elivro.model.Categoria;
import com.leandrorego.elivro.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	/**
	 * Método para realização do cadastro das informações via rest
	 * 
	 * @param cat
	 * @return
	 */
	@PostMapping
	public ResponseEntity<Categoria> adicionar(@RequestBody @Valid Categoria cat) {

		if (cat != null) {
			service.save(cat);
			return new ResponseEntity<Categoria>(cat, HttpStatus.CREATED);
		}
		return new ResponseEntity<Categoria>(cat, HttpStatus.CONFLICT);

	}

	@GetMapping("/listar")
	public List<Categoria> listar() {
		return service.listaAll();
	}

	/**
	 * Retorna o objeto que foi encontrado a partir do ID
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping("/buscar/{id}")
	public ResponseEntity<Categoria> buscar(@PathVariable Long id) {
		Categoria c = service.findOne(id);

		// Retorna 404
		if (c == null) {
			return ResponseEntity.notFound().build();
		}

		// Retornando o objeto que foi encontrado
		return ResponseEntity.ok(c);

	}

	@PutMapping("/{id}")
	public ResponseEntity<Categoria> atualizar(@PathVariable Long id, @RequestBody Categoria categoriaAlterada) {

		Categoria existente = service.findOne(id);

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
		Categoria cat = service.findOne(id);

		// Retorna 404
		if (cat == null) {
			return ResponseEntity.notFound().build();
		}
		
		//apagando
		service.delete(id);
			
		// Retorno de sucesso, porém sem conteúdo.
		return ResponseEntity.noContent().build();

	}

}
