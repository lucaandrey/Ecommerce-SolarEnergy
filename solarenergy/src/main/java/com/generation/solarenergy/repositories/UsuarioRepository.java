package com.generation.solarenergy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.solarenergy.models.ProdutoModels;
import com.generation.solarenergy.models.UsuarioModels;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModels, Long>{
	public List<UsuarioModels> findAllByNomeConteiningIgnoreCase (String nome);
}	
