package com.senai.vitor.bibliotecabackend.Dashboard;

import com.senai.vitor.bibliotecabackend.Entidades.Emprestimo;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class DashboardDTO {
    private Long totalLivros;
    private Long totalDisponiveis;
    private Long totalEmprestados;
    private Long totalEmprestimosAtivos;
    private List<Emprestimo> ultimosEmprestimos;
}
