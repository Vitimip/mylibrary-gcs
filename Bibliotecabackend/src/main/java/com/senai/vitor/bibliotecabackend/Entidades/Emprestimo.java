package com.senai.vitor.bibliotecabackend.Entidades;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Emprestimo")
@Builder
public class Emprestimo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @Column(nullable = false)
    private String nomePessoa;

    private String telefone;

    private LocalDateTime dataEmprestimo;

    private LocalDateTime dataDevolucaoPrevista;

    private LocalDateTime dataDevolucaoEfetiva;
}
