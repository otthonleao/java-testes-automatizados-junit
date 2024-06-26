package dev.otthon.biblioteca.services;

import dev.otthon.biblioteca.builders.ClienteBuilder;
import dev.otthon.biblioteca.builders.EmprestimoBuilder;
import dev.otthon.biblioteca.builders.ObraBuilder;
import dev.otthon.biblioteca.dao.EmprestimoDAO;
import dev.otthon.biblioteca.enums.Reputacao;
import dev.otthon.biblioteca.models.Obra;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmprestimoServiceTest {

    @Mock
    private EmprestimoDAO emprestimoDAO;

    @Mock
    private NotificacaoService notificacaoService;

    @InjectMocks
    private EmprestimoService emprestimoService;

    @Test
    void quandoMetodoNovoForChamadoDeveRetornarUmEmprestimo() {
        // CENÁRIO
        var cliente = ClienteBuilder.builder().build();
        var obra = ObraBuilder.builder().build();

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
        var cliente = ClienteBuilder.builder().reputacao(Reputacao.RUIM).build();
        var obra = ObraBuilder.builder().build();
        // EXECUÇÃO
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));
        // VERIFICAÇÃO
        assertEquals(LocalDate.now().plusDays(1), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoRegularDeveRetornarUmEmprestimoEmTresDias() {
        // CENÁRIO
        var cliente = ClienteBuilder.builder().build();
        var obra = ObraBuilder.builder().build();
        // EXECUÇÃO
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));
        // VERIFICAÇÃO
        assertEquals(LocalDate.now().plusDays(3), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteDeReputacaoBoaDeveRetornarUmEmprestimoEmCincoDias() {
        // CENÁRIO
        var cliente = ClienteBuilder.builder().reputacao(Reputacao.BOA).build();
        var obra = ObraBuilder.builder().build();
        // EXECUÇÃO
        var emprestimo = emprestimoService.novo(cliente, List.of(obra));
        // VERIFICAÇÃO
       assertEquals(LocalDate.now().plusDays(5), emprestimo.getDataDevolucao());
    }

    @Test
    void quandoMetodoNovoForChamadoComObraNuloDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // CENÁRIO
        var cliente = ClienteBuilder.builder().build();
        var mensagemEsperada = "Obras não podem ser nulo e nem vazio";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, null));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNovoForChamadoComObraVaziaDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // CENÁRIO
        var cliente = ClienteBuilder.builder().build();
        var obras = new ArrayList<Obra>();
        var mensagemEsperada = "Obras não podem ser nulo e nem vazio";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(cliente, obras));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNovoForChamadoComClienteNuloDeveLancarUmaExcecaoDoTipoIllegalArgumentException() {
        // CENÁRIO
        var obra = ObraBuilder.builder().build();
        var mensagemEsperada = "Cliente não pode ser nulo";

        var exception = assertThrows(IllegalArgumentException.class, () -> emprestimoService.novo(null, List.of(obra)));
        assertEquals(mensagemEsperada, exception.getMessage());
    }

    @Test
    void quandoMetodoNotificarAtrasoForChamadoDeveRetornarONumeroDeNotificacoes() {
        // CENÁRIO
        var emprestimos = List.of(
                EmprestimoBuilder.builder().build(),
                EmprestimoBuilder.builder().dataDevolucao(LocalDate.now().minusDays(1)).build()
        );
        Mockito.when(emprestimoDAO.buscarTodos()).thenReturn(emprestimos);

        // EXECUÇÃO
        emprestimoService.notificarAtrasos();

        // VERIFICAÇÃO
        Mockito.verify(notificacaoService).notificar(emprestimos.get(1));
    }
}
