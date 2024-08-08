package com.lisandrojimenez.webapp.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandrojimenez.webapp.Biblioteca.model.Libro;
import com.lisandrojimenez.webapp.Biblioteca.repository.LibroRepository;

@Service
public class LibroService implements ILibroService{
    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Libro> listarLibro() {
        return libroRepository.findAll();
    }

    @Override
    public Libro buscarLibroPorId(Long id) {
        return libroRepository.findById(id).orElse(null);
    }

    @Override
    public Libro guardarLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    @Override
    public void eliminarLibro(Libro libro) {
        libroRepository.delete(libro);
    }
}
