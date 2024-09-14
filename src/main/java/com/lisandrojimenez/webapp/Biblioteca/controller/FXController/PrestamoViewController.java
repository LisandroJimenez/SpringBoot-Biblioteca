package com.lisandrojimenez.webapp.Biblioteca.controller.FXController;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lisandrojimenez.webapp.Biblioteca.model.Cliente;
import com.lisandrojimenez.webapp.Biblioteca.model.Empleado;
import com.lisandrojimenez.webapp.Biblioteca.model.Libro;
import com.lisandrojimenez.webapp.Biblioteca.model.Prestamo;
import com.lisandrojimenez.webapp.Biblioteca.service.ClienteService;
import com.lisandrojimenez.webapp.Biblioteca.service.EmpleadoService;
import com.lisandrojimenez.webapp.Biblioteca.service.LibroService;
import com.lisandrojimenez.webapp.Biblioteca.service.PrestamoService;
import com.lisandrojimenez.webapp.Biblioteca.system.Main;
import com.lisandrojimenez.webapp.Biblioteca.util.MethodType;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class PrestamoViewController implements Initializable {

    @Setter
    private Main stage;

    @FXML
    private TextField tfID, tfBuscar;

    @FXML
    private DatePicker dpFechaPrestamo, dpFechaDevolucion;

    @FXML
    private ComboBox<Libro> cmbLibro;

    @FXML
    private ComboBox<Boolean> cmbVigencia;

    @FXML
    private ComboBox<Empleado> cmbEmpleado;

    @FXML
    private ComboBox<Cliente> cmbCliente;

    @FXML
    private TableView<Prestamo> tblPrestamo;

    @FXML
    private TableColumn<Prestamo, Long> colId;

    @FXML
    private TableColumn<Prestamo, Date> colFechaPrestamo, colFechaDevolucion;

    @FXML
    private TableColumn<Prestamo, List<Libro>> colLibro;

    @FXML
    private TableColumn<Prestamo, Boolean> colVigencia;

    @FXML
    private TableColumn<Prestamo, Empleado> colEmpleado;

    @FXML
    private TableColumn<Prestamo, Cliente> colCliente;

    @FXML
    private Button btnGuardar, btnLimpiar, btnEliminar, btnBuscar;

    @Autowired
    private PrestamoService prestamoService;

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private LibroService libroService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        cargarLibros();
        cargarEmpleados();
        cargarClientes();
        cargarVigencia();
    }

    public void cargarDatos() {
        tblPrestamo.setItems(listarPrestamos());
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colFechaPrestamo.setCellValueFactory(new PropertyValueFactory<>("fechaDePrestamo"));
        colFechaDevolucion.setCellValueFactory(new PropertyValueFactory<>("fechaDeDevolucion"));
        colLibro.setCellValueFactory(new PropertyValueFactory<>("libros"));
        colVigencia.setCellValueFactory(new PropertyValueFactory<>("vigencia"));
        colEmpleado.setCellValueFactory(new PropertyValueFactory<>("empleado"));
        colCliente.setCellValueFactory(new PropertyValueFactory<>("cliente"));
    }

    public void cargarLibros() {
        List<Libro> libros = libroService.listarLibro();
        cmbLibro.setItems(FXCollections.observableList(libros));
    }

    public void cargarEmpleados() {
        List<Empleado> empleados = empleadoService.listarEmpleados();
        cmbEmpleado.setItems(FXCollections.observableList(empleados));
    }

    public void cargarClientes() {
        List<Cliente> clientes = clienteService.listarCliente();
        cmbCliente.setItems(FXCollections.observableList(clientes));
        
    }

    public void cargarVigencia() {
        cmbVigencia.setItems(FXCollections.observableArrayList(true, false));
    }

    public void cargarDatosEditar() {
        Prestamo prestamo = tblPrestamo.getSelectionModel().getSelectedItem();
        if (prestamo != null) {
            tfID.setText(prestamo.getId().toString());
            dpFechaPrestamo.setValue(prestamo.getFechaDePrestamo().toLocalDate());
            dpFechaDevolucion.setValue(prestamo.getFechaDeDevolucion().toLocalDate());
            cmbLibro.setValue(prestamo.getLibros().get(0));  // Suponiendo que es solo un libro
            cmbVigencia.setValue(prestamo.getVigencia());
            cmbEmpleado.setValue(prestamo.getEmpleado());
            cmbCliente.setValue(prestamo.getCliente());
        }
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (!tfID.getText().isBlank()) {
                editarPrestamo();
            } else {
                agregarPrestamo();
            }
        } else if (event.getSource() == btnLimpiar) {
            limpiarForm();
        } else if (event.getSource() == btnEliminar) {
            eliminarPrestamo();
        } else if (event.getSource() == btnBuscar) {
            buscarPrestamo();
        }
    }

    public void limpiarForm() {
        tfID.clear();
        dpFechaPrestamo.setValue(null);
        dpFechaDevolucion.setValue(null);
        cmbLibro.setValue(null);
        cmbEmpleado.setValue(null);
        cmbCliente.setValue(null);
        cmbVigencia.setValue(null);
    }

    public ObservableList<Prestamo> listarPrestamos() {
        return FXCollections.observableList(prestamoService.listarPrestamos());
    }

    public void agregarPrestamo() {
        Prestamo prestamo = new Prestamo();
        prestamo.setFechaDePrestamo(Date.valueOf(dpFechaPrestamo.getValue()));
        prestamo.setFechaDeDevolucion(Date.valueOf(dpFechaDevolucion.getValue()));
        prestamo.setLibros(List.of(cmbLibro.getValue())); 
        prestamo.setVigencia(cmbVigencia.getValue());
        prestamo.setEmpleado(cmbEmpleado.getValue());
        prestamo.setCliente(cmbCliente.getValue());
        prestamoService.guardarPrestamo(prestamo, MethodType.POST);
        cargarDatos();
    } 

    public void editarPrestamo() {
        Prestamo prestamo = prestamoService.buscarPrestamoPorId(Long.parseLong(tfID.getText()));
        prestamo.setFechaDePrestamo(Date.valueOf(dpFechaPrestamo.getValue()));
        prestamo.setFechaDeDevolucion(Date.valueOf(dpFechaDevolucion.getValue()));
        prestamo.setLibros(List.of(cmbLibro.getValue()));
        prestamo.setVigencia(cmbVigencia.getValue());
        prestamo.setEmpleado(cmbEmpleado.getValue());
        prestamo.setCliente(cmbCliente.getValue());
        prestamoService.guardarPrestamo(prestamo, MethodType.PUT);
        cargarDatos();
    }

    public void eliminarPrestamo() {
        Prestamo prestamo = prestamoService.buscarPrestamoPorId(Long.parseLong(tfID.getText()));
        prestamoService.eliminarPrestamo(prestamo);
        cargarDatos();
    }

    public void buscarPrestamo() {
        String buscarId = tfBuscar.getText();
        if (!buscarId.isBlank()) {
            try {
                Long id = Long.parseLong(buscarId);
                Prestamo prestamo = prestamoService.buscarPrestamoPorId(id);
                tblPrestamo.setItems(FXCollections.observableArrayList(prestamo));
            } catch (Exception e) {
                // Manejo de excepción
            }
        } else {
            cargarDatos();
        }
    }
}
