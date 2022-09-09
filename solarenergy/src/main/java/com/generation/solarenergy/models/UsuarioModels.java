package com.generation.solarenergy.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_usuarios")
public class UsuarioModels {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Size(max = 255)
	private String nome;
	
	@NotBlank
	@Size(max = 255)
	private String email;
	
	@NotBlank
	@Size(max = 255)
	private String foto;
	
	@NotBlank
	@Size(max = 255)
	private String senha;
	
	@NotBlank
	@Size(max = 20)
	private String CPF;
	
	@NotBlank
	@Size(max = 255)
	private String endereco;
	
	@OneToMany(mappedBy ="usuario", cascade = CascadeType.ALL )
	@JsonIgnoreProperties("produto")
	private List<ProdutoModels> produto; 
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public List<ProdutoModels> getProduto() {
		return produto;
	}

	public void setProduto(List<ProdutoModels> produto) {
		this.produto = produto;
	}

	
	
	
}
