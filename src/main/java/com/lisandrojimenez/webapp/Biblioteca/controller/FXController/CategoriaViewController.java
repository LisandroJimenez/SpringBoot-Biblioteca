package com.lisandrojimenez.webapp.Biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lisandrojimenez.webapp.Biblioteca.model.Categoria;
import com.lisandrojimenez.webapp.Biblioteca.service.CategoriaService;
import com.lisandrojimenez.webapp.Biblioteca.system.Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class CategoriaViewController implements Initializable{
    @Setter
    private Main stage;

    @FXML
    TextField tfId, tfNombre, tfBuscar;
    @FXML
    TableView tvCategoria;
    @FXML
    TableColumn colId, colNombre;
    @Autowired
    CategoriaService categoriaService;
    @FXML
    Button btnGuardar, btnLimpiar, btnEliminar, btnBuscar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();        
    }

    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnGuardar) {
            if(tfId.getText().isBlank()){
                agregarCategoria();
            }else{
                editarCategoria();
            }
        }else if (event.getSource() == btnLimpiar) {
            limpiarForm();
        }else if (event.getSource() == btnBuscar) {
            buscarCategoria();
        }else if (event.getSource() == btnEliminar) {
            eliminarCategoria();
        }
    }

    public void cargarDatos(){
        tvCategoria.setItems(listarCategorias());
        colId.setCellValueFactory(new PropertyValueFactory<Categoria, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Categoria, String>("nombreCategoria"));
    }

    public void cargarDatosEditar(){
        Categoria categoria = (Categoria)tvCategoria.getSelectionModel().getSelectedItem();
        if (categoria != null) {
            tfId.setText(Long.toString(categoria.getId()));
            tfNombre.setText(categoria.getNombreCategoria());
        }
    }

    public void limpiarForm(){
        tfId.clear();
        tfNombre.clear();
    }

    public ObservableList<Categoria> listarCategorias(){
        return FXCollections.observableList(categoriaService.listarCategorias());
    }

    public void agregarCategoria(){
        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void editarCategoria(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoria.setNombreCategoria(tfNombre.getText());
        categoriaService.guardarCategoria(categoria);
        cargarDatos();
    }

    public void eliminarCategoria(){
        Categoria categoria = categoriaService.buscarCategoriaPorId(Long.parseLong(tfId.getText()));
        categoriaService.eliminarCategoria(categoria);
        cargarDatos();
    }

    public void buscarCategoria() {
        String buscarId = tfBuscar.getText(); 
        if (!buscarId.isBlank()) {
            try {
                Long id = Long.parseLong(buscarId);
                Categoria categoria = categoriaService.buscarCategoriaPorId(id);
                tvCategoria.setItems(FXCollections.observableArrayList(categoria)); 
            } catch (NumberFormatException e) {
                System.out.println("ID no válido");
            }
        }else{
            cargarDatos();
        }
    }
    
}
