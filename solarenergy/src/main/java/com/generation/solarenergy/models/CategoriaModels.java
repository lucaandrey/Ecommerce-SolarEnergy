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
@Table(name = "tb_categorias")
public class CategoriaModels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
   
    @NotBlank(message = "O atributo é obrigatorio")
    @Size(min = 5, max = 255)
    private String tipoProduto;
    
    @NotBlank(message = "O atributo é obrigatorio")
    @Size(min = 5, max = 255)
    private String descricao;
    
    @OneToMany(mappedBy ="categoria", cascade = CascadeType.ALL )
	@JsonIgnoreProperties("categoria")
	private List<ProdutoModels> produto; 
    
    public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(String tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

	public List<ProdutoModels> getProduto() {
		return produto;
	}

	public void setProduto(List<ProdutoModels> produto) {
		this.produto = produto;
	}

    
}