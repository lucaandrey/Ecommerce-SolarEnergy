package com.generation.solarenergy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.solarenergy.models.ProdutoModels;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoModels, Long>{
	
	public List<ProdutoModels> findAllByMarcaConteiningIgnoreCase (String marca);
}
