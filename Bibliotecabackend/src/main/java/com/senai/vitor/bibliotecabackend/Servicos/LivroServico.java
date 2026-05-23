package com.senai.vitor.bibliotecabackend.Servicos;

import com.senai.vitor.bibliotecabackend.DTOs.LivroDTO;
import com.senai.vitor.bibliotecabackend.Entidades.Categoria;
import com.senai.vitor.bibliotecabackend.Entidades.Livro;
import com.senai.vitor.bibliotecabackend.Entidades.Status;
import com.senai.vitor.bibliotecabackend.Repositorios.CategoriaRepositorio;
import com.senai.vitor.bibliotecabackend.Repositorios.LivroRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LivroServico {
    private final LivroRepositorio livroRepository;
    private final CategoriaRepositorio categoriaRepository;

    public Livro salvar(LivroDTO dto) {

        Categoria categoria = categoriaRepository.findById(dto.getCategoriaId())
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        Livro livro = Livro.builder()
                .titulo(dto.getTitulo())
                .autor(dto.getAutor())
                .isbn(dto.getIsbn())
                .ano(dto.getAno())
                .status(Status.DISPONIVEL)
                .categoria(categoria)
                .build();

        return livroRepository.save(livro);
    }
    public void excluir(Long id) {

        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livro.getStatus() == Status.EMPRESTADO) {
            throw new RuntimeException("Livro emprestado não pode ser excluído");
        }

        livroRepository.delete(livro);
    }
}
