package com.senai.vitor.bibliotecabackend.Controladores;

import com.senai.vitor.bibliotecabackend.Entidades.Categoria;
import com.senai.vitor.bibliotecabackend.Repositorios.CategoriaRepositorio;
import com.senai.vitor.bibliotecabackend.Servicos.CategoriaServico;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class CategoriaControlador{
    private final CategoriaServico categoriaService;
    private final CategoriaRepositorio categoriaRepository;

    @GetMapping
    public List<Categoria> listar() {
        return categoriaRepository.findAll();
    }

    @PostMapping
    public Categoria salvar(@RequestBody Categoria categoria) {
        return categoriaService.salvar(categoria);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        categoriaService.excluir(id);
    }
}
