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

import com.generation.solarenergy.models.CategoriaModels;
import com.generation.solarenergy.repositories.CategoriaRepository;

@RequestMapping("/categoria")
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
	public ResponseEntity <List<CategoriaModels>> getAll (){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findAll());
	}

	@GetMapping ("/descricao/{descricao}")
	public ResponseEntity<List<CategoriaModels>> getByDescricao (@PathVariable String descricao){
		return ResponseEntity.status(HttpStatus.OK).body(categoriaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoriaModels> getById (@PathVariable Long id){
		return categoriaRepository.findById (id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping 
	public ResponseEntity<CategoriaModels> post (@Valid @RequestBody CategoriaModels categoriaModels){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaRepository.save(categoriaModels));
	}

	@PutMapping
	public ResponseEntity<CategoriaModels> put (@Valid @RequestBody CategoriaModels categoriaModels){
		return categoriaRepository.findById(categoriaModels.getId()).
				map(resp -> ResponseEntity.ok(categoriaRepository.save(categoriaModels)))
				.orElse(ResponseEntity.notFound().build());
		}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		Optional<CategoriaModels> categoriaModels = categoriaRepository.findById(id);
		if (categoriaModels.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		categoriaRepository.deleteById(id);
	}
	
	
}



