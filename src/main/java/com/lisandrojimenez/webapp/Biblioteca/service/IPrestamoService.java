package com.lisandrojimenez.webapp.Biblioteca.service;

import java.util.List;

import com.lisandrojimenez.webapp.Biblioteca.model.Prestamo;

public interface IPrestamoService {
    public List<Prestamo> listarPrestamos();

    public Prestamo buscarPrestamoPorId(Long id);

    public Prestamo guardarPrestamo(Prestamo prestamo);

    public void eliminarPrestamo(Prestamo prestamo);
}
