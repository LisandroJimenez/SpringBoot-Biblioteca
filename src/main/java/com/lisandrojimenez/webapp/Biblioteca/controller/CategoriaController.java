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
import com.lisandrojimenez.webapp.Biblioteca.model.Categoria;
import com.lisandrojimenez.webapp.Biblioteca.service.CategoriaService;

@Controller
@RestController
@RequestMapping(value = "")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    //listar
    @GetMapping("/categorias")
    public List<Categoria> listaCategorias() {
        return categoriaService.listarCategorias();
    }

    //Buscar
    @GetMapping("/categoria")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(categoriaService.buscarCategoriaPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //agregar
    @PostMapping("/categoria")
    public ResponseEntity<Map<String, Boolean>> agregarCategoria(@RequestBody Categoria categoria){
        Map<String, Boolean> response = new HashMap<>();

        try{
            if(categoriaService.guardarCategoria(categoria)){
                response.put("se agrego con exito", true);
                return ResponseEntity.ok(response);
            }else{
                response.put("no se pudo agregar", false);
            return ResponseEntity.badRequest().body(response);
            }
        }catch(Exception e){
            response.put("no se pudo agregar", false);
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Editar
    @PutMapping("/categoria")
    public ResponseEntity<Map<String, String>> editarCategoria(@RequestParam Long id, @RequestBody Categoria categoriaNueva){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoria.setNombreCategoria(categoriaNueva.getNombreCategoria());
            categoriaService.guardarCategoria(categoria);
            response.put("message","Categoria se ha editado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "No se pudo editar la categoria");
            return ResponseEntity.badRequest().body(response);
        }
    }

    
    //Eliminar
    @DeleteMapping("/categoria")
    public ResponseEntity<Map<String, String>>eliminarCategoria(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);
            response.put("message", "Eliminado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "La categoria no se pudo eliminar");
            return ResponseEntity.badRequest().body(response);
        }
    }

    
}
