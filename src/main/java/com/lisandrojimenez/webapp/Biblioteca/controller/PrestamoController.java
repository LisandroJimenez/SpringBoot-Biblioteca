package com.lisandrojimenez.webapp.Biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lisandrojimenez.webapp.Biblioteca.model.Prestamo;
import com.lisandrojimenez.webapp.Biblioteca.service.PrestamoService;

@Controller
@RestController
@RequestMapping(value = "")
public class PrestamoController {
    @Autowired
    PrestamoService prestamoService;

    @GetMapping("/prestamos")
    public List<Prestamo>listarPrestamos(){
        return prestamoService.listarPrestamos();
    }

    @GetMapping("/prestamo")
    public ResponseEntity<Prestamo> buscarPrestamoPorId(@RequestParam  Long id){
        try {
            return ResponseEntity.ok(prestamoService.buscarPrestamoPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PostMapping("/prestamo")
    public ResponseEntity<Map<String, String>> guardarPrestamo(@RequestBody Prestamo prestamo){
        Map<String,String> response = new HashMap<>();
        try {
            prestamoService.guardarPrestamo(prestamo);
            response.put("message", "se agrego correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "error al agregar el prestamo");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/prestamo")
    public ResponseEntity <Map<String,String>>editarPrestamo(@RequestParam Long id, @RequestBody Prestamo prestamoNuevo){
        Map<String, String> response = new HashMap<>();
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            prestamo.setCliente(prestamoNuevo.getCliente());
            prestamo.setEmpleado(prestamoNuevo.getEmpleado());
            prestamo.setFechaDeDevolucion(prestamoNuevo.getFechaDeDevolucion());
            prestamo.setFechaDePrestamo(prestamoNuevo.getFechaDePrestamo());
            prestamo.setLibros(prestamoNuevo.getLibros());
            prestamo.setVigencia(prestamoNuevo.getVigencia());
            prestamoService.guardarPrestamo(prestamo);
            response.put("message", "se edito con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "no se pudo editar");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @DeleteMapping("/prestamo")
    public ResponseEntity <Map<String, String>> eliminarPrestamo(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
            prestamoService.eliminarPrestamo(prestamo);
            response.put("message","eliminado con exito ");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "no se pudo eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
