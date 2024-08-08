package com.lisandrojimenez.webapp.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lisandrojimenez.webapp.Biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

}
