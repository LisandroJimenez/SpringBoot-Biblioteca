package com.lisandrojimenez.webapp.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lisandrojimenez.webapp.Biblioteca.model.Prestamo;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long>{

}
