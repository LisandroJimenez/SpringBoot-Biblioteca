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
import com.lisandrojimenez.webapp.Biblioteca.model.Libro;
import com.lisandrojimenez.webapp.Biblioteca.service.LibroService;

@Controller
@RestController
@RequestMapping(value = "")
public class LibroController {
    @Autowired
    LibroService libroService;

    //listar
    @GetMapping("/libros")
    public List<Libro> listarLibros(){
        return libroService.listarLibro();
    }

    // buscar
    @GetMapping("/libro")
    public ResponseEntity<Libro> buscarLibroPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(libroService.buscarLibroPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //agregar
    @PostMapping("/libro")
    public ResponseEntity<Map<String,String>> guardarLibro(@RequestBody Libro libro){
        Map<String, String> response = new HashMap<>();

        try {
            libroService.guardarLibro(libro);
            response.put("message", "se agrego correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "error al agregar el libro");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //editaar
    @PutMapping("/libro")
    public ResponseEntity<Map<String, String>> editarLibro(@RequestParam Long id, @RequestBody Libro libroNuevo){
        Map<String, String> response = new HashMap<>();
        try {
            Libro libro = libroService.buscarLibroPorId(id);
            libro.setIsbn(libroNuevo.getIsbn());
            libro.setNombre(libroNuevo.getNombre());
            libro.setSinopsis(libroNuevo.getSinopsis());
            libro.setAutor(libroNuevo.getAutor());
            libro.setEditorial(libroNuevo.getEditorial());
            libro.setDisponibilidad(libroNuevo.getDisponibilidad());
            libro.setNumeroEstanteria(libroNuevo.getNumeroEstanteria());
            libro.setCluster(libroNuevo.getCluster());
            libro.setCategoria(libroNuevo.getCategoria());
            libroService.guardarLibro(libro);
            response.put("message", "Libro editado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "no se pudo editar el Libro");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Eliminar
    @DeleteMapping("/libro")
    public ResponseEntity<Map<String,String>> eliminarLibro(@RequestParam long id){
        Map<String, String> response = new HashMap<>();
        try {
            Libro libro = libroService.buscarLibroPorId(id);
            libroService.eliminarLibro(libro);
            response.put("message", "Libro eliminado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "no se pudo eliminar el Libro");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
