package com.lisandrojimenez.webapp.Biblioteca.controller.FXController;

import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lisandrojimenez.webapp.Biblioteca.model.Categoria;
import com.lisandrojimenez.webapp.Biblioteca.model.Cliente;
import com.lisandrojimenez.webapp.Biblioteca.service.ClienteService;
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
public class ClienteViewController implements Initializable{

    @Setter
    private Main stage;

    @FXML
    TextField tfDPI, tfNombre, tfApellido, tfTelefono, tfBuscar;
    @FXML
    TableView tblClientes;
    @FXML
    TableColumn colDPI, colNombre, colApellido, colTelefono;
    @FXML
    Button btnGuardar, btnLimpiar, btnEliminar, btnBuscar;
    
    @Autowired
    ClienteService clienteService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
    }
    
    public void handleButtonAction(ActionEvent event){
        if (event.getSource() == btnGuardar) {
            if(!tfDPI.getText().isBlank()){
                agregarCliente();
            }else{
                editarCliente();
            }
        }else if (event.getSource() == btnLimpiar) {
            limpiarForm();
        }else if (event.getSource() == btnBuscar) {
            buscarCliente();
        }else if (event.getSource() == btnEliminar) {
            eliminarCliente();
        }
    }

    public void cargarDatos(){
        tblClientes.setItems(listarCliente());
        colDPI.setCellValueFactory(new PropertyValueFactory<Cliente, Long>("dpi"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));
        colApellido.setCellValueFactory(new PropertyValueFactory<Cliente, String>("apellido"));
        colTelefono.setCellValueFactory(new PropertyValueFactory<Cliente, String>("telefono"));
    }

    public void cargarDatosEditar(){
        Cliente cliente = (Cliente)tblClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            tfDPI.setText(Long.toString(cliente.getDpi()));
            tfNombre.setText(cliente.getNombre());
            tfApellido.setText(cliente.getApellido());
            tfTelefono.setText(cliente.getTelefono());
        }
    }

    public void limpiarForm(){
        tfDPI.clear();
        tfNombre.clear();
        tfApellido.clear();
        tfTelefono.clear();
    }

    public ObservableList<Cliente> listarCliente(){
        return FXCollections.observableList(clienteService.listarCliente());
    }

    public void agregarCliente(){
        Cliente cliente = new Cliente();
        cliente.setDpi(Long.parseLong(tfDPI.getText()));
        cliente.setNombre(tfNombre.getText());
        cliente.setApellido(tfApellido.getText());
        cliente.setTelefono(tfTelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void editarCliente(){
        Cliente cliente = clienteService.buscarClientePorId(Long.parseLong(tfDPI.getText()));
        cliente.setNombre(tfNombre.getText());
        cliente.setApellido(tfApellido.getText());
        cliente.setTelefono(tfTelefono.getText());
        clienteService.guardarCliente(cliente);
        cargarDatos();
    }

    public void eliminarCliente(){
        Cliente cliente = clienteService.buscarClientePorId(Long.parseLong(tfDPI.getText()));
        clienteService.eliminarCliente(cliente);
        cargarDatos();
    }

    public void buscarCliente(){
        String buscarId = tfBuscar.getText();
        if (!buscarId.isBlank()) {
            try {
                Long id = Long.parseLong(buscarId);
                Cliente cliemte = clienteService.buscarClientePorId(id);
                tblClientes.setItems(FXCollections.observableArrayList(cliemte));
            } catch (Exception e) {
                // TODO: handle exception
            }
        }else{
            cargarDatos();
        }
    }
    
}
