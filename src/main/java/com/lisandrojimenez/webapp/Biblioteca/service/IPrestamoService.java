package com.lisandrojimenez.webapp.Biblioteca.service;

import java.util.List;

import com.lisandrojimenez.webapp.Biblioteca.model.Prestamo;
import com.lisandrojimenez.webapp.Biblioteca.util.MethodType;

public interface IPrestamoService {
    public List<Prestamo> listarPrestamos();

    public Prestamo buscarPrestamoPorId(Long id);

    public Boolean guardarPrestamo(Prestamo prestamo, MethodType methodType);

    public void eliminarPrestamo(Prestamo prestamo);

    public Boolean verificarCliente(Prestamo prestamo);
}
