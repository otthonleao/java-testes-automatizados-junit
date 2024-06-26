package dev.otthon.biblioteca.services;

import dev.otthon.biblioteca.dao.EmprestimoDAO;
import dev.otthon.biblioteca.models.Cliente;
import dev.otthon.biblioteca.models.Emprestimo;
import dev.otthon.biblioteca.models.Obra;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoService {

    private EmprestimoDAO emprestimoDAO;
    private NotificacaoService notificacaoService;

    public EmprestimoService(EmprestimoDAO emprestimoDAO, NotificacaoService notificacaoService) {
        this.emprestimoDAO = emprestimoDAO;
        this.notificacaoService = notificacaoService;
    }

    public Emprestimo novo(Cliente cliente, List<Obra> obras){

        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser nulo");
        }

        if (obras == null || obras.isEmpty()) {
            throw new IllegalArgumentException("Obras não podem ser nulo e nem vazio");
        }

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

    public void notificarAtrasos() {
        var hoje = LocalDate.now();

        var emprestimos = emprestimoDAO.buscarTodos();

        for (Emprestimo emprestimo : emprestimos) {
            var estaAtrasado = emprestimo.getDataDevolucao().isBefore(hoje);
            if (estaAtrasado) {
                notificacaoService.notificar(emprestimo);
            }
        }
    }
}
