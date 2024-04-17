package dev.otthon.biblioteca.services;

import dev.otthon.biblioteca.enums.Reputacao;
import dev.otthon.biblioteca.enums.Tipo;
import dev.otthon.biblioteca.models.Autor;
import dev.otthon.biblioteca.models.Cliente;
import dev.otthon.biblioteca.models.Obra;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

public class EmprestimoServiceTest {
    @Test
    void quandoMetodoNovoForChamadoDeveRetornarUmEmprestimo() {
        // CENÁRIO
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        // EXECUÇÃO
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // VERIFICAÇÃO
        Assertions.assertEquals(cliente, emprestimo.getCliente());
        Assertions.assertEquals(List.of(obra), emprestimo.getLivros());
        Assertions.assertEquals(LocalDate.now(), emprestimo.getDataEmprestimo());
        Assertions.assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoRuimDeveRetornarUmEmprestimoEmUmDia() {
        // CENÁRIO
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        // EXECUÇÃO
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // VERIFICAÇÃO
        Assertions.assertEquals(LocalDate.now().plusDays(1), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoRegularDeveRetornarUmEmprestimoEmTresDias() {
        // CENÁRIO
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        // EXECUÇÃO
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // VERIFICAÇÃO
        Assertions.assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoBoaDeveRetornarUmEmprestimoEmCincoDias() {
        // CENÁRIO
        var emprestimoService = new EmprestimoService();
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        // EXECUÇÃO
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // VERIFICAÇÃO
        Assertions.assertEquals(LocalDate.now().plusDays(5), emprestimo.getDataDevolucao());
    }
}
