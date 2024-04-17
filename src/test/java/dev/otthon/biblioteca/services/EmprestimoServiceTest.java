package dev.otthon.biblioteca.services;

import dev.otthon.biblioteca.enums.Reputacao;
import dev.otthon.biblioteca.enums.Tipo;
import dev.otthon.biblioteca.models.Autor;
import dev.otthon.biblioteca.models.Cliente;
import dev.otthon.biblioteca.models.Obra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EmprestimoServiceTest {

    private EmprestimoService emprestimoService;

    @BeforeEach
    public void setUp() {
        emprestimoService = new EmprestimoService();
    }


    @Test
    void quandoMetodoNovoForChamadoDeveRetornarUmEmprestimo() {
        // CENÁRIO
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        // EXECUÇÃO
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));

        // VERIFICAÇÃO
        assertEquals(cliente, emprestimo.getCliente());
        assertEquals(List.of(obra), emprestimo.getLivros());
        assertEquals(LocalDate.now(), emprestimo.getDataEmprestimo());
        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoRuimDeveRetornarUmEmprestimoEmUmDia() {
        // CENÁRIO
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);
        // EXECUÇÃO
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));
        // VERIFICAÇÃO
        assertEquals(LocalDate.now().plusDays(1), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoRegularDeveRetornarUmEmprestimoEmTresDias() {
        // CENÁRIO
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);
        // EXECUÇÃO
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));
        // VERIFICAÇÃO
        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoBoaDeveRetornarUmEmprestimoEmCincoDias() {
        // CENÁRIO
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);
        // EXECUÇÃO
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));
        // VERIFICAÇÃO
       assertEquals(LocalDate.now().plusDays(5), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComObraNuloDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // CENÁRIO
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var mensagemEsperada = "Obras não podem ser nulo e nem vazio";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, null));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNovoForChamadoComObraVaziaDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // CENÁRIO
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        var obras = new ArrayList<Obra>();
        var mensagemEsperada = "Obras não podem ser nulo e nem vazio";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, obras));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteNuloDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // CENÁRIO
        var autor = new Autor(1L, "Autor Teste", LocalDate.now(), null);
        var obra = new Obra(1L, "Obra Teste", 100, Tipo.LIVRO, autor);

        var mensagemEsperada = "Cliente não pode ser nulo";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(null, List.of(obra)));
        assertEquals(mensagemEsperada, exception.getMessage());
    }
}
