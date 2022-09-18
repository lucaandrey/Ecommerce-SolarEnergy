package com.generation.solarenergy.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.solarenergy.models.UsuarioModels;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModels, Long>{
	public Optional<UsuarioModels> findByUsuario (String usuario);
	
	public List<UsuarioModels> findAllByNomeContainingIgnoreCase(String name);
}	
