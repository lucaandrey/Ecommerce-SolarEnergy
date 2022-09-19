package com.generation.solarenergy.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.generation.solarenergy.models.CategoriaModels;
import com.generation.solarenergy.models.UsuarioModels;
import com.generation.solarenergy.repositories.CategoriaRepository;
import com.generation.solarenergy.repositories.UsuarioRepository;
import com.generation.solarenergy.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CategoriaControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start(){

		usuarioRepository.deleteAll();

		usuarioService.cadastrarUsuario(new UsuarioModels(0L, 
			"Admin", "admin@root.com", "adminroot", "ajhofiafjasifaishfa", "12345678912345", "sadasdasda21s1as4"));

	}
	
	@Test
	public void createCategory() {
		
		HttpEntity<CategoriaModels> request = new HttpEntity<CategoriaModels>(new CategoriaModels(0L, "placa solar", "kit placas"));
		
		ResponseEntity<CategoriaModels> response = testRestTemplate
				.withBasicAuth("admin@root.com", "adminroot")
				.exchange("/categoria", HttpMethod.POST, request, CategoriaModels.class);
		
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(request.getBody().getTipoProduto(), response.getBody().getTipoProduto());
		assertEquals(request.getBody().getDescricao(), response.getBody().getDescricao());
	}
	
	@Test
	public void updateCategory() {
		
		CategoriaModels category = new CategoriaModels(1L, "placa solar", "kit placas");
		
		Optional<CategoriaModels> c1 = categoriaRepository.findById(category.getId());
		
		CategoriaModels updateCategory = new CategoriaModels(c1.get().getId(), "bateria", "Carga 600W");
		
		HttpEntity<CategoriaModels> request = new HttpEntity<CategoriaModels>(updateCategory);
		
		ResponseEntity<CategoriaModels> response = testRestTemplate
				.withBasicAuth("admin@root.com", "adminroot")
				.exchange("/categoria", HttpMethod.PUT, request, CategoriaModels.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(request.getBody().getTipoProduto(), response.getBody().getTipoProduto());
		assertEquals(request.getBody().getDescricao(), response.getBody().getDescricao());
	}
	
	@Test
	public void showAll() {
		
		CategoriaModels category = new CategoriaModels(1L, "placa solar", "kit placas");
		CategoriaModels category2 = new CategoriaModels(2L, "placa solar", "kit placas");
		
		ResponseEntity<String> response = testRestTemplate
				.withBasicAuth("admin@root.com", "adminroot")
				.exchange("/categoria", HttpMethod.GET, null, String.class);
		
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	
}
