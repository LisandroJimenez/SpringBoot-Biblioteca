package com.lisandrojimenez.webapp.Biblioteca.service;

import java.util.List;

import com.lisandrojimenez.webapp.Biblioteca.model.Libro;


public interface ILibroService {
    public List<Libro> listarLibro();

    public Libro buscarLibroPorId(Long id);

    public Libro guardarLibro(Libro libro);

    public void eliminarLibro(Libro libro);
}
