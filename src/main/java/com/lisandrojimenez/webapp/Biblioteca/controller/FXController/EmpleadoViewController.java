package com.lisandrojimenez.webapp.Biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lisandrojimenez.webapp.Biblioteca.model.Empleado;
import com.lisandrojimenez.webapp.Biblioteca.service.EmpleadoService;
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
public class EmpleadoViewController implements Initializable{
    @Setter
    private Main stage;
    @FXML
    TextField tfID, tfDPI, tfNombre, tfApellido, tfTelefono, tfDireccion, tfBuscar;
    @FXML
    TableView tblEmpleado;
    @FXML
    TableColumn colId, colNombre, colApellido, colTelefono, colDireccion, colDPI;
    
    @FXML
    Button btnGuardar, btnLimpiar, btnEliminar, btnBuscar;

    @Autowired
    EmpleadoService empleadoService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }

    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnGuardar) {
            if(tfID.getText().isBlank()){
                agregarEmpleado();
            }else{
                editarEmpleado();
            }
        }else if (event.getSource() == btnLimpiar) {
            limpiarForm();
        }else if (event.getSource() == btnBuscar) {
            buscarEmpleado();
        }else if (event.getSource() == btnEliminar) {
            eliminarEmpleado();
        }
    }

    public void cargarDatos(){
        tblEmpleado.setItems(listarEmpleado());
        colId.setCellValueFactory(new PropertyValueFactory<Empleado, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Empleado, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Empleado, String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Empleado, String>("telefono"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<Empleado, String>("direccion"));
        colDPI.setCellValueFactory(new PropertyValueFactory<Empleado, String>("dpi"));
        
    }

    public void cargarDatosEditar(){
        Empleado empleado = (Empleado)tblEmpleado.getSelectionModel().getSelectedItem();
        if (empleado != null) {
            tfID.setText(Long.toString(empleado.getId()));
            tfNombre.setText(empleado.getNombre());
            tfApellido.setText(empleado.getApellido());
            tfTelefono.setText(empleado.getTelefono());
            tfDireccion.setText(empleado.getDireccion());
            tfDPI.setText(empleado.getDpi());
        }
    }

    public void limpiarForm(){
        tfID.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfTelefono.clear();
        tfDireccion.clear();
        tfDPI.clear();
    }

    public ObservableList<Empleado>listarEmpleado(){
        return FXCollections.observableList(empleadoService.listarEmpleados());
    }

    public void agregarEmpleado(){
        Empleado empleado = new Empleado();
        empleado.setNombre(tfNombre.getText());
        empleado.setApellido(tfApellido.getText());
        empleado.setTelefono(tfTelefono.getText());
        empleado.setDireccion(tfDireccion.getText());
        empleado.setDpi(tfDPI.getText());
        empleadoService.guardarEmpleado(empleado);
        cargarDatos();
    }

    public void editarEmpleado(){
        Empleado empleado = empleadoService.buscarEmpleadoPorId(Long.parseLong(tfID.getText()));
        empleado.setNombre(tfNombre.getText());
        empleado.setApellido(tfApellido.getText());
        empleado.setTelefono(tfTelefono.getText());
        empleado.setDireccion(tfDireccion.getText());
        empleado.setDpi(tfDPI.getText());
        empleadoService.guardarEmpleado(empleado);
        cargarDatos();
    }

    public void eliminarEmpleado(){
        Empleado empleado = empleadoService.buscarEmpleadoPorId(Long.parseLong(tfID.getText()));
        empleadoService.eliminarEmpleado(empleado);
        cargarDatos();
    }

    public void buscarEmpleado(){
        String buscarId = tfBuscar.getText();
        if (!buscarId.isBlank()) {
            try {
                Long id = Long.parseLong(buscarId);
                Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
                tblEmpleado.setItems(FXCollections.observableArrayList(empleado));
            } catch (Exception e) {
                // TODO: handle exception
            }
        }else{
            cargarDatos();
        }
    }
}
