package com.senai.vitor.bibliotecabackend.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmprestimoDTO {

    private Long id;
    private Long livroId;
    private String livroTitulo;
    private String nomePessoa;
    private String telefone;
    private LocalDateTime dataEmprestimo;
    private LocalDateTime dataDevolucaoPrevista;
    private LocalDateTime dataDevolucaoEfetiva;
}
