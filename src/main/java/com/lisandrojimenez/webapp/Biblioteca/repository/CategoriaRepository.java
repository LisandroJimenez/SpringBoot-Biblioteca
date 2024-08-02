package com.lisandrojimenez.webapp.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lisandrojimenez.webapp.Biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {

}
