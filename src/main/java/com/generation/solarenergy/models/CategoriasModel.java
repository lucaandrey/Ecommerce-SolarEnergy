package com.generation.solarenergy.models;

@Entity
@Table(name = "tb_categorias")
public class CategoriasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O atributo eh obrigatorio")
    @Size(min = 5, max = 255)
    private String usuario;

    @NotBlank()
    @Size(min = 5, max = 255)
    private String tipoProduto;


    

}
