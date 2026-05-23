package com.senai.vitor.bibliotecabackend.Servicos;

import com.senai.vitor.bibliotecabackend.DTOs.EmprestimoDTO;
import com.senai.vitor.bibliotecabackend.Entidades.Emprestimo;
import com.senai.vitor.bibliotecabackend.Entidades.Livro;
import com.senai.vitor.bibliotecabackend.Entidades.Status;
import com.senai.vitor.bibliotecabackend.Repositorios.EmprestimoRepositorio;
import com.senai.vitor.bibliotecabackend.Repositorios.LivroRepositorio;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class EmprestimoServico {
    private final EmprestimoRepositorio emprestimoRepository;
    private final LivroRepositorio livroRepository;

    @Transactional
    public Emprestimo emprestar(EmprestimoDTO dto) {

        Livro livro = livroRepository.findById(dto.getLivroId())
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        if (livro.getStatus() == Status.EMPRESTADO) {
            throw new RuntimeException("Livro já emprestado");
        }

        livro.setStatus(Status.EMPRESTADO);

        Emprestimo emprestimo = Emprestimo.builder()
                .livro(livro)
                .nomePessoa(dto.getNomePessoa())
                .telefone(dto.getTelefone())
                .dataEmprestimo(LocalDateTime.now())
                .dataDevolucaoPrevista(dto.getDataDevolucaoPrevista())
                .build();
        livroRepository.save(livro);

        return emprestimoRepository.save(emprestimo);
    }

    @Transactional
    public Emprestimo devolver(Long id) {

        Emprestimo emprestimo = emprestimoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        if (emprestimo.getDataDevolucaoEfetiva() != null) {
            throw new RuntimeException("Livro já devolvido");
        }

        Livro livro = emprestimo.getLivro();
        livro.setStatus(Status.DISPONIVEL);

        emprestimo.setDataDevolucaoEfetiva(LocalDateTime.now());

        livroRepository.save(livro);

        return emprestimoRepository.save(emprestimo);
    }
}
