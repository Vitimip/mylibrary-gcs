package com.senai.vitor.bibliotecabackend.Controladores;

import com.senai.vitor.bibliotecabackend.DTOs.LivroDTO;
import com.senai.vitor.bibliotecabackend.Entidades.Livro;
import com.senai.vitor.bibliotecabackend.Entidades.Status;
import com.senai.vitor.bibliotecabackend.Repositorios.LivroRepositorio;
import com.senai.vitor.bibliotecabackend.Servicos.LivroServico;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/livros")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class LivroControlador{
    private final LivroRepositorio livroRepository;
    private final LivroServico livroService;

    @GetMapping
    public List<Livro> listar(
            @RequestParam(required = false) Long categoriaId,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) String busca
    ) {
        return livroRepository.buscarComFiltros(categoriaId, status, busca);
    }

    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    @PostMapping
    public Livro salvar(@RequestBody LivroDTO dto) {
        return livroService.salvar(dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        livroService.excluir(id);
    }
}
