package com.lisandrojimenez.webapp.Biblioteca.service;

import java.util.List;

import com.lisandrojimenez.webapp.Biblioteca.model.Categoria;
import com.lisandrojimenez.webapp.Biblioteca.model.Cliente;

public interface IClienteService {
    public List<Cliente> listarCliente();

    public Cliente buscarClientePorId(Long id);

    public Cliente guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);
}
