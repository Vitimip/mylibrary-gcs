package com.senai.vitor.bibliotecabackend.Dashboard;

import com.senai.vitor.bibliotecabackend.Entidades.Status;
import com.senai.vitor.bibliotecabackend.Repositorios.EmprestimoRepositorio;
import com.senai.vitor.bibliotecabackend.Repositorios.LivroRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardControlador {
    private final LivroRepositorio livroRepository;
    private final EmprestimoRepositorio emprestimoRepository;

    @GetMapping
    public DashboardDTO dashboard() {

        Long totalLivros = livroRepository.count();

        Long totalDisponiveis = livroRepository.findAll()
                .stream()
                .filter(l -> l.getStatus() == Status.DISPONIVEL)
                .count();
        Long totalEmprestados = livroRepository.findAll()
                .stream()
                .filter(l -> l.getStatus() == Status.EMPRESTADO)
                .count();

        Long ativos = emprestimoRepository.findByDataDevolucaoEfetivaIsNull()
                .stream()
                .count();

        return DashboardDTO.builder()
                .totalLivros(totalLivros)
                .totalDisponiveis(totalDisponiveis)
                .totalEmprestados(totalEmprestados)
                .totalEmprestimosAtivos(ativos)
                .ultimosEmprestimos(
                        emprestimoRepository.findTop5ByOrderByDataEmprestimoDesc()
                )
                .build();
    }
}
