package com.generation.solarenergy.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
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

import com.generation.solarenergy.models.UsuarioModels;
import com.generation.solarenergy.repositories.UsuarioRepository;
import com.generation.solarenergy.service.UsuarioService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioControllerTest {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
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
	public void createUser() {
		HttpEntity<UsuarioModels> request = new HttpEntity<UsuarioModels>(new UsuarioModels(0L, 
			"Jose Silva", "jose_silva@email.com.br", "13465278", " ajhofiafjasifaishfa ", "12345678912345", "sadasdasda21s1as4"));

		ResponseEntity<UsuarioModels> response = testRestTemplate
			.exchange("/usuarios/cadastrar", HttpMethod.POST, request, UsuarioModels.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(request.getBody().getNome(), response.getBody().getNome());
		assertEquals(request.getBody().getUsuario(), response.getBody().getUsuario());
		
	}
	
	@Test
	public void shouldntDuplicateUser() {

		usuarioService.cadastrarUsuario(new UsuarioModels(0L, 
			"Ana Santos", "ana_santos@email.com.br", "13465278", "randomrandom","12345678912345", "sadasdasda21s1as4"));

		HttpEntity<UsuarioModels> bodyRequest = new HttpEntity<UsuarioModels>(new UsuarioModels(0L, 
			"Ana Santos", "ana_santos@email.com.br", "13465278", "randomrandom","12345678912345", "sadasdasda21s1as4"));

		ResponseEntity<UsuarioModels> response = testRestTemplate
			.exchange("/usuarios/cadastrar", HttpMethod.POST, bodyRequest, UsuarioModels.class);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
	}
	
	@Test
	@DisplayName("Atualizar um Usuário")
	public void updateUser() {

		Optional<UsuarioModels> usuarioCadastrado = usuarioService.cadastrarUsuario(new UsuarioModels(0L, 
			"Juliana Andrews", "juliana_andrews@email.com.br", "juliana123", "ajhofiafjasifaishfa","12345678912345", "sadasdasda21s1as4"));

		UsuarioModels usuarioUpdate = new UsuarioModels(usuarioCadastrado.get().getId(), 
			"Juliana Andrews Ramos", "juliana_ramos@email.com.br", "juliana123" , "ajhofiafjasifaishfa","12345678912345", "sadasdasda21s1as4");
		
		HttpEntity<UsuarioModels> corpoRequisicao = new HttpEntity<UsuarioModels>(usuarioUpdate);

		ResponseEntity<UsuarioModels> corpoResposta = testRestTemplate
			.withBasicAuth("admin@root.com", "adminroot")
			.exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, UsuarioModels.class);

		assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
		assertEquals(corpoRequisicao.getBody().getNome(), corpoResposta.getBody().getNome());
		assertEquals(corpoRequisicao.getBody().getUsuario(), corpoResposta.getBody().getUsuario());
	}
	
	@Test
	public void showAll() {

		usuarioService.cadastrarUsuario(new UsuarioModels(0L, 
				"Maria da Silva", "maria_silva@email.com.br", "13465278", "randomrandom","12345678912345", "sadasdasda21s1as4"));
		
		usuarioService.cadastrarUsuario(new UsuarioModels(0L, 
				"Clovis de Barros Filho", "professorclovis@email.com.br", "13465278", "randomrandom","12345678912345", "sadasdasda21s1as4"));

		ResponseEntity<String> resposta = testRestTemplate
		.withBasicAuth("admin@root.com", "adminroot")
			.exchange("/usuarios/all", HttpMethod.GET, null, String.class);

		assertEquals(HttpStatus.OK, resposta.getStatusCode());

	}
	
	
}

