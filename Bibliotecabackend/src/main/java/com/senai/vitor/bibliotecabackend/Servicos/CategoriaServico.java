package com.senai.vitor.bibliotecabackend.Servicos;

import com.senai.vitor.bibliotecabackend.Entidades.Categoria;
import com.senai.vitor.bibliotecabackend.Repositorios.CategoriaRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaServico {
    private final CategoriaRepositorio categoriaRepository;

    public Categoria salvar(Categoria categoria) {

        if (categoriaRepository.existsByNomeIgnoreCase(categoria.getNome())) {
            throw new RuntimeException("Categoria já cadastrada");
        }

        return categoriaRepository.save(categoria);
    }

    public void excluir(Long id) {

        Categoria categoria = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada"));

        if (!categoria.getLivros().isEmpty()) {
            throw new RuntimeException("Categoria possui livros vinculados");
        }

        categoriaRepository.delete(categoria);
    }
}
