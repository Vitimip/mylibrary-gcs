package com.senai.vitor.bibliotecabackend.Repositorios;

import com.senai.vitor.bibliotecabackend.Entidades.Emprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDateTime;
import java.util.List;

public interface EmprestimoRepositorio extends JpaRepository<Emprestimo, Long> {
    List<Emprestimo> findByDataDevolucaoEfetivaIsNull();

    @Query("""
        SELECT e FROM Emprestimo e
        WHERE e.dataDevolucaoPrevista < :hoje
        AND e.dataDevolucaoEfetiva IS NULL
    """)
    List<Emprestimo> buscarAtrasados(LocalDateTime hoje);

    List<Emprestimo> findTop5ByOrderByDataEmprestimoDesc();
}
