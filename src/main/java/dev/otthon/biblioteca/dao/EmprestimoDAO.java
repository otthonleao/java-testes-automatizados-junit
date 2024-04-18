package dev.otthon.biblioteca.dao;

import dev.otthon.biblioteca.models.Emprestimo;

import java.util.List;

public interface EmprestimoDAO {

    List<Emprestimo> buscarTodos();

}
