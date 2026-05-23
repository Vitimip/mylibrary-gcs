package com.senai.vitor.bibliotecabackend.Repositorios;

import com.senai.vitor.bibliotecabackend.Entidades.Livro;
import com.senai.vitor.bibliotecabackend.Entidades.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepositorio extends JpaRepository<Livro, Long> {
    @Query("""
        SELECT l FROM Livro l
        WHERE (:categoriaId IS NULL OR l.categoria.id = :categoriaId)
        AND (:status IS NULL OR l.status = :status)
        AND (
            :busca IS NULL OR
            LOWER(l.titulo) LIKE LOWER(CONCAT('%', :busca, '%')) OR
            LOWER(l.autor) LIKE LOWER(CONCAT('%', :busca, '%'))
        )
    """)
    List<Livro> buscarComFiltros(
            Long categoriaId,
            Status status,
            String busca
    );
}
