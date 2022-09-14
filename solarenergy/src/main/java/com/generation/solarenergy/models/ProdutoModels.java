package com.generation.solarenergy.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_produtos")
public class ProdutoModels {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String nomeProduto;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String marca;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String dimensao;
	
	
	private Double preco;
	
	
	private Integer quantidade;
	
	@NotBlank
	private String material;
	
	@NotBlank
	private String potencia;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private UsuarioModels usuario;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private CategoriaModels categoria;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getDimensao() {
		return dimensao;
	}

	public void setDimensao(String dimensao) {
		this.dimensao = dimensao;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getPotencia() {
		return potencia;
	}

	public void setPotencia(String potencia) {
		this.potencia = potencia;
	}

	public UsuarioModels getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModels usuario) {
		this.usuario = usuario;
	}

	public CategoriaModels getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaModels categoria) {
		this.categoria = categoria;
	}
	
	
	
}
