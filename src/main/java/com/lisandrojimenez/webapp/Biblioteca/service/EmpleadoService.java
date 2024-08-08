package com.lisandrojimenez.webapp.Biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lisandrojimenez.webapp.Biblioteca.model.Empleado;
import com.lisandrojimenez.webapp.Biblioteca.repository.EmpleadoRepositoy;

@Service
public class EmpleadoService implements IEmpleadoService{
    @Autowired
    private EmpleadoRepositoy empleadoRepositoy;

    @Override
    public List<Empleado> listarEmpleados(){
        return empleadoRepositoy.findAll();
    }

    @Override
    public Empleado buscarEmpleadoPorId(Long id){
        return empleadoRepositoy.findById(id).orElse(null);
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepositoy.save(empleado);
    }

    @Override
    public void eliminarEmpleado(Empleado empleado){
        empleadoRepositoy.delete(empleado);
    }

}
