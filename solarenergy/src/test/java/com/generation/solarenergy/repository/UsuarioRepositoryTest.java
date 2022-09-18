package com.generation.solarenergy.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.generation.solarenergy.models.UsuarioModels;
import com.generation.solarenergy.repositories.UsuarioRepository;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioRepositoryTest {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start() {
		usuarioRepository.deleteAll();
		
		usuarioRepository.save(new UsuarioModels(0L, "Jose da Costa", "jose@email.com", "123456789", "fototesterr.test", "12345678912345", "sadasdasda21s1as4"));
		
		usuarioRepository.save(new UsuarioModels(0L, "Ana da Costa", "ana@email.com", "123456789", "fototesterr.test", "12345678912345", "sadasdasda21s1as4"));
		
		usuarioRepository.save(new UsuarioModels(0L, "Maria da Costa", "maria@email.com", "123456789", "fototesterr.test", "12345678912345", "sadasdasda21s1as4"));
		
		usuarioRepository.save(new UsuarioModels(0L, "Pedro Aquino", "pedro@email.com", "123456789", "fototesterr.test", "12345678912345", "sadasdasda21s1as4"));
	}
	
	@Test
	public void return1User() {
		Optional<UsuarioModels> user = usuarioRepository.findByUsuario("jose@email.com");
		
		assertTrue(user.get().getUsuario().equals("jose@email.com"));
	}
	
	@Test
	public void shouldReturn3Users() {
		List<UsuarioModels> usersList = usuarioRepository.findAllByNomeContainingIgnoreCase("Costa");
		assertEquals(3, usersList.size());
		assertTrue(usersList.get(0).getNome().equals("Jose da Costa"));
		assertTrue(usersList.get(1).getNome().equals("Ana da Costa"));
		assertTrue(usersList.get(2).getNome().equals("Maria da Costa"));
	}
	
	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
	}
	
}
