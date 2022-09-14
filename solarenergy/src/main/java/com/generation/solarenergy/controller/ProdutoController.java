package com.generation.solarenergy.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.solarenergy.models.ProdutoModels;
import com.generation.solarenergy.repositories.CategoriaRepository;
import com.generation.solarenergy.repositories.ProdutoRepository;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity<List<ProdutoModels>> getAll() {
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProdutoModels> getById(@PathVariable Long id) {
		return produtoRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/marca/{marca}")
	public ResponseEntity <List<ProdutoModels>> getByMarca(@PathVariable String marca) {
		return ResponseEntity.ok(produtoRepository.findAllByMarcaContainingIgnoreCase(marca));
	}
	
	@PostMapping
	public ResponseEntity<ProdutoModels> post(@RequestBody @Valid ProdutoModels produtoModels) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModels));
		
		if(categoriaRepository.existsById(produtoModels.getCategoria().getId()))
			return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produtoModels));
		return ResponseEntity.notFound().build();
					
	}
	
	@PutMapping
	public ResponseEntity<ProdutoModels> put(@RequestBody @Valid ProdutoModels produtoModels) {
		return produtoRepository.findById(produtoModels.getId())
				.map(resp -> ResponseEntity.ok(produtoRepository.save(produtoModels)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional <ProdutoModels> produtoModels = produtoRepository.findById(id);
			if (produtoModels.isEmpty())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		produtoRepository.deleteById(id);	
	}

}
