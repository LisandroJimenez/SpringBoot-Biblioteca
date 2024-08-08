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

import com.lisandrojimenez.webapp.Biblioteca.model.Cliente;
import com.lisandrojimenez.webapp.Biblioteca.service.ClienteService;


@Controller
@RestController
@RequestMapping(value = "")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    //listar
    @GetMapping("/clientes")
    public List<Cliente> listarClientes(){
        return clienteService.listarCliente();
    }

    // buscar
    @GetMapping("/cliente")
    public ResponseEntity<Cliente> buscarClientePorId(@RequestParam Long id){
        try {
            return ResponseEntity.ok(clienteService.buscarClientePorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    //agregar
    @PostMapping("/cliente")
    public ResponseEntity<Map<String,String>> agregarCliente(@RequestBody Cliente cliente){
        Map<String, String> response = new HashMap<>();

        try {
            clienteService.guardarCliente(cliente);
            response.put("message", "se agrego correctamente");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "error al agregar el Cliente");
            return ResponseEntity.badRequest().body(response);
        }
    }
    
    //editaar
    @PutMapping("/cliente")
    public ResponseEntity<Map<String, String>> editarCliente(@RequestParam Long id, @RequestBody Cliente clienteNuevo){
        Map<String, String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorId(id);
            cliente.setDpi(clienteNuevo.getDpi());
            cliente.setNombre(clienteNuevo.getNombre());
            cliente.setApellido(clienteNuevo.getApellido());
            cliente.setTelefono(clienteNuevo.getTelefono());
            clienteService.guardarCliente(cliente);
            response.put("message", "Cliente editado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "no se pudo editar el Cliente");
            return ResponseEntity.badRequest().body(response);
        }
    }

    //Eliminar
    @DeleteMapping("/cliente")
    public ResponseEntity<Map<String,String>> eliminarCliente(@RequestParam long id){
        Map<String, String> response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorId(id);
            clienteService.eliminarCliente(cliente);
            response.put("message", "Cliente eliminado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "no se pudo eliminar el CLiente");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
