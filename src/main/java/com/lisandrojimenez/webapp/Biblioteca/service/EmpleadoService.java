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
    public Boolean guardarEmpleado(Empleado empleado) {
        if(!verificarDpiDuplicado(empleado)){
            empleadoRepositoy.save(empleado);
            return true;
        }
        return false;
    }

    @Override
    public void eliminarEmpleado(Empleado empleado){
        empleadoRepositoy.delete(empleado);
    }

    @Override
    public Boolean verificarDpiDuplicado(Empleado empleadoNuevo) {
        List<Empleado> empleados = listarEmpleados();
        Boolean flag = false;
        for (Empleado empleado2 : empleados) {
            if(empleado2.getDpi().equals(empleadoNuevo.getDpi()) && !empleado2.getId().equals(empleadoNuevo.getId())){
                flag = true;
            }
        }
        return flag;
    }

}
