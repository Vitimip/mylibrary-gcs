package com.senai.vitor.bibliotecabackend.Controladores;

import com.senai.vitor.bibliotecabackend.DTOs.EmprestimoDTO;
import com.senai.vitor.bibliotecabackend.Entidades.Emprestimo;
import com.senai.vitor.bibliotecabackend.Repositorios.EmprestimoRepositorio;
import com.senai.vitor.bibliotecabackend.Servicos.EmprestimoServico;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/emprestimos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmprestimoControlador{
    private final EmprestimoRepositorio emprestimoRepository;
    private final EmprestimoServico emprestimoService;

    @GetMapping
    public List<Emprestimo> listar() {
        return emprestimoRepository.findAll();
    }

    @GetMapping("/ativos")
    public List<Emprestimo> ativos() {
        return emprestimoRepository.findByDataDevolucaoEfetivaIsNull();
    }

    @GetMapping("/atrasados")
    public List<Emprestimo> atrasados() {
        return emprestimoRepository.buscarAtrasados(LocalDateTime.now());
    }

    @PostMapping("/emprestar")
    public Emprestimo emprestar(@RequestBody EmprestimoDTO dto) {
        return emprestimoService.emprestar(dto);
    }

    @PostMapping("/{id}/devolver")
    public Emprestimo devolver(@PathVariable Long id) {
        return emprestimoService.devolver(id);
    }
}
