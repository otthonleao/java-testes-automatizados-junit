package dev.otthon.biblioteca.services;

import dev.otthon.biblioteca.models.Emprestimo;

public interface NotificacaoService {

    void notificar(Emprestimo emprestimo);

}
