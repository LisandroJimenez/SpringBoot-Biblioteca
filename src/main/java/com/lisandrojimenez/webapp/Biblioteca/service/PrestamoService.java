package com.lisandrojimenez.webapp.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandrojimenez.webapp.Biblioteca.model.Prestamo;
import com.lisandrojimenez.webapp.Biblioteca.repository.LibroRepository;
import com.lisandrojimenez.webapp.Biblioteca.repository.PrestamoRepository;
import com.lisandrojimenez.webapp.Biblioteca.util.MethodType;

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
    public Boolean guardarPrestamo(Prestamo prestamo, MethodType methodType) {
        if (methodType.equals(methodType.POST)) {
            if (!verificarCliente(prestamo)) {
                prestamoRepository.save(prestamo);
                return true;
            }else{
                return false;
            }
        }else if(methodType.equals(methodType.PUT)){
            prestamoRepository.save(prestamo);
            return true; 
        }
        return true;

    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamoRepository.delete(prestamo);

    }

    @Override
    public Boolean verificarCliente(Prestamo prestamoNuevo) {
        List<Prestamo> prestamos = listarPrestamos();
        Boolean flag = false;
        for (Prestamo prestamo : prestamos) {
            if (prestamo.getCliente().getDpi().equals(prestamoNuevo.getCliente().getDpi()) && !prestamo.getId().equals(prestamoNuevo.getId())) {
                return true;
            }
        }
        return flag;
    }

    

}
