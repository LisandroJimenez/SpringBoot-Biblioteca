package com.lisandrojimenez.webapp.Biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lisandrojimenez.webapp.Biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
