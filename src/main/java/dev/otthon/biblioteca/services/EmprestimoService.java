package dev.otthon.biblioteca.services;

import dev.otthon.biblioteca.enums.Reputacao;
import dev.otthon.biblioteca.models.Cliente;
import dev.otthon.biblioteca.models.Emprestimo;
import dev.otthon.biblioteca.models.Obra;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {

    public Emprestimo novo(Cliente cliente, List<Obra> obras){

        var emprestimo = new Emprestimo();

        var dataEmprestimo = LocalDate.now();
        // Aplicando o Design Pattern Strategy, os dias de acordo com a reputação está no Enum
        var diasParaDevolucao = cliente.getReputacao().obterDiasParaDevolucao();
        var dataDevolucao = dataEmprestimo.plusDays(diasParaDevolucao);

        emprestimo.setCliente(cliente);
        emprestimo.setLivros(obras);
        emprestimo.setDataEmprestimo(dataEmprestimo);
        emprestimo.setDataDevolucao(dataDevolucao);

        return emprestimo;
    }

}
