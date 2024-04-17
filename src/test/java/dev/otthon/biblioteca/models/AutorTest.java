package dev.otthon.biblioteca.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class AutorTest {

    @Test
    void quandoMetodoEstaVivoForChamadoComDataFalecimentoNulaDeveRetornarTrue() {

        // CENÁRIO - Qual cenário será testado?
        var autor = new Autor();
        // EXECUÇÃO - O código a ser testado.
        var estaVivo = autor.estaVivo();
        // VERIFICAÇÃO - Teste propriamente dito.
        Assertions.assertEquals(true, estaVivo);
    }

   @Test
    void quandoMetodoEstaVivoForChamadoComDataFalecimentoPreenchidoDeveRetornarFalse() {
        var autor = new Autor();
        autor.setDataFalecimento(LocalDate.now());

        var estaVivo = autor.estaVivo();
        Assertions.assertEquals(false, estaVivo);
    }
}
