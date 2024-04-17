package dev.otthon.biblioteca.builders;

import dev.otthon.biblioteca.enums.Reputacao;
import dev.otthon.biblioteca.models.Cliente;

import java.time.LocalDate;

public class ClienteBuilder {

    private Cliente cliente;

    public static ClienteBuilder builder() {
        var builder = new ClienteBuilder();

        // Valores padrões dos clientes
        var cliente = new Cliente(1L, "Cliente Teste", LocalDate.now(), "123.123.123-11", Reputacao.REGULAR);
        builder.cliente = cliente;

        return builder;
    }

    public ClienteBuilder reputacao(Reputacao reputacao) {
        cliente.setReputacao(reputacao);
        return this;
    }

    public Cliente build() {
        return cliente;
    }

}
