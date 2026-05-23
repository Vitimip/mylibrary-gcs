package com.senai.vitor.bibliotecabackend.Repositorios;

import com.senai.vitor.bibliotecabackend.Entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Long> {
    boolean existsByNomeIgnoreCase(String nome);
}
