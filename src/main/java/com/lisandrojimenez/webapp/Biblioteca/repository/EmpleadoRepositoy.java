package com.lisandrojimenez.webapp.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lisandrojimenez.webapp.Biblioteca.model.Empleado;

public interface EmpleadoRepositoy extends JpaRepository<Empleado, Long>{

}
