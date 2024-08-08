package com.lisandrojimenez.webapp.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandrojimenez.webapp.Biblioteca.model.Prestamo;
import com.lisandrojimenez.webapp.Biblioteca.repository.LibroRepository;
import com.lisandrojimenez.webapp.Biblioteca.repository.PrestamoRepository;

@Service
public class PrestamoService implements IPrestamoService {

    @Autowired
    private PrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo buscarPrestamoPorId(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamoRepository.delete(prestamo);

    }

}
