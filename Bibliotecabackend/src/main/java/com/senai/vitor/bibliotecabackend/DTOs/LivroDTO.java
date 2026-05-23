package com.senai.vitor.bibliotecabackend.DTOs;

import com.senai.vitor.bibliotecabackend.Entidades.Status;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LivroDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Integer ano;
    private Status status;
    private Long categoriaId;
    private String categoriaNome;
}
