package com.lisandrojimenez.webapp.Biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.lisandrojimenez.webapp.Biblioteca.system.Main;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import lombok.Setter;
import javafx.fxml.Initializable;

@Component
public class IndexController implements Initializable{
    @FXML
    MenuItem categoria, cliente, empleado, libro, prestamo;

    @Setter
    private Main stage;

    public void handleButtonAction(ActionEvent event){
        if(event.getSource() == categoria){
            stage.categoriaView();
        }else if (event.getSource() == cliente) {
            stage.clienteView();
        }else if (event.getSource() == empleado) {
            stage.empleadoView();
        }else if (event.getSource() == libro) {
            stage.libroView();
        }else if (event.getSource() == prestamo) {
            stage.prestamoView();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // TODO Auto-generated method stub
        
    }

    
}
