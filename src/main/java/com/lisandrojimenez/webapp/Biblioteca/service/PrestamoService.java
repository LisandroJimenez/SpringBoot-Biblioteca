package com.lisandrojimenez.webapp.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandrojimenez.webapp.Biblioteca.model.Libro;
import com.lisandrojimenez.webapp.Biblioteca.model.Prestamo;
import com.lisandrojimenez.webapp.Biblioteca.repository.LibroRepository;
import com.lisandrojimenez.webapp.Biblioteca.repository.PrestamoRepository;
import com.lisandrojimenez.webapp.Biblioteca.util.MethodType;

@Service
public class PrestamoService implements IPrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    private LibroRepository libroRepository;

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo buscarPrestamoPorId(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public Boolean guardarPrestamo(Prestamo prestamo, MethodType methodType) {
        if (methodType.equals(MethodType.POST)) {
            if (verificarDisponibilidadLibros(prestamo)) {
                if (!verificarCliente(prestamo)) {
                    for (Libro libro : prestamo.getLibros()) {
                        libro.setDisponibilidad(false);
                        libroRepository.save(libro);
                    }
                    prestamoRepository.save(prestamo);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else if (methodType.equals(MethodType.PUT)) {
            prestamoRepository.save(prestamo);
            return true;
        }
        return false;
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        for (Libro libro : prestamo.getLibros()) {
            libro.setDisponibilidad(true);
            libroRepository.save(libro);
        }
        prestamoRepository.delete(prestamo);
    }

    @Override
    public Boolean verificarCliente(Prestamo prestamoNuevo) {
        List<Prestamo> prestamos = listarPrestamos();
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCliente().getDpi().equals(prestamoNuevo.getCliente().getDpi())
                    && prestamo.getVigencia()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Boolean verificarDisponibilidadLibros(Prestamo prestamo) {
        for (Libro libro : prestamo.getLibros()) {
            if (libro.getDisponibilidad() == null || !libro.getDisponibilidad()) {
                return false;
            }
        }
        return true;
    }

}
