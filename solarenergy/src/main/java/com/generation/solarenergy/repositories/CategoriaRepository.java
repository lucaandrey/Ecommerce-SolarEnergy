package com.generation.solarenergy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.generation.solarenergy.models.CategoriaModels;

@Repository

public interface CategoriaRepository extends JpaRepository<CategoriaModels, Long> {

	public List <CategoriaModels> findAllByDescricaoContainingIgnoreCase (String descricao);
	
}
