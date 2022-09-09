package com.generation.solarenergy.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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
import org.springframework.web.server.ResponseStatusException;

import com.generation.solarenergy.models.CategoriaModels;
import com.generation.solarenergy.models.UsuarioModels;
import com.generation.solarenergy.repositories.UsuarioRepository;

@RequestMapping("/usuario")
@RestController
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@GetMapping
	public ResponseEntity<List<UsuarioModels>> getAll() {
		return ResponseEntity.ok(usuarioRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModels> getById(@PathVariable Long id) {
		return usuarioRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());				
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<UsuarioModels>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(usuarioRepository.findAllByNomeConteiningIgnoreCase(nome));
	}
	
	@PostMapping 
	public ResponseEntity<UsuarioModels> post (@Valid @RequestBody UsuarioModels usuarioModels){
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioRepository.save(usuarioModels));
	}

	@PutMapping
	public ResponseEntity<UsuarioModels> put (@Valid @RequestBody UsuarioModels usuarioModels){
		return usuarioRepository.findById(usuarioModels.getId()).
				map(resp -> ResponseEntity.ok(usuarioRepository.save(usuarioModels)))
				.orElse(ResponseEntity.notFound().build());
		}
	
	@DeleteMapping("/{id}")
	public void delete (@PathVariable Long id) {
		Optional<UsuarioModels> usuarioModels = usuarioRepository.findById(id);
		if (usuarioModels.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		usuarioRepository.deleteById(id);
	}
	
	
	
}
