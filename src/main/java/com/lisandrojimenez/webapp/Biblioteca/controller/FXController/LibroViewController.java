package com.lisandrojimenez.webapp.Biblioteca.controller.FXController;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lisandrojimenez.webapp.Biblioteca.model.Categoria;
import com.lisandrojimenez.webapp.Biblioteca.model.Libro;
import com.lisandrojimenez.webapp.Biblioteca.service.CategoriaService;
import com.lisandrojimenez.webapp.Biblioteca.service.LibroService;
import com.lisandrojimenez.webapp.Biblioteca.system.Main;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Setter;

@Component
public class LibroViewController implements Initializable {

    @Setter
    private Main stage;

    @FXML
    TextField tfID, tfNombre, tfAutor, tfEditorial, tfEstanteria, tfCluster, tfIsbn, tfBuscar;
    @FXML
    TextArea taSinopsis;
    @FXML
    ComboBox<Categoria> cmbCategoria;
    @FXML
    ComboBox<Boolean> cmbDisponibilidad;
    @FXML
    TableView<Libro> tblLibro;
    @FXML
    TableColumn colId, colNombre, colSinopsis, colAutor, colEditorial, colEstanteria, colCluster, colCategoria, colDisponibilidad; 
    @FXML
    Button btnGuardar, btnLimpiar, btnEliminar, btnBuscar;

    @Autowired
    private LibroService libroService;

    @Autowired
    private CategoriaService categoriaService;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarDatos();
        cargarCategorias();
        cargarDisponibilidad();
    }

    public void cargarDatos() {
        tblLibro.setItems(listarLibro());
        colId.setCellValueFactory(new PropertyValueFactory<Libro, Long>("id"));
        colNombre.setCellValueFactory(new PropertyValueFactory<Libro, String>("nombre"));
        colSinopsis.setCellValueFactory(new PropertyValueFactory<Libro, String>("sinopsis"));
        colAutor.setCellValueFactory(new PropertyValueFactory<Libro, String>("autor"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<Libro, String>("editorial"));
        colEstanteria.setCellValueFactory(new PropertyValueFactory<Libro, String>("numeroEstanteria"));
        colCluster.setCellValueFactory(new PropertyValueFactory<Libro, String>("cluster"));
        colCategoria.setCellValueFactory(new PropertyValueFactory<Libro, String>("categoria"));
        colDisponibilidad.setCellValueFactory(new PropertyValueFactory<Libro, Boolean>("disponibilidad"));
    }

    public void cargarCategorias() {
        List<Categoria> categorias = categoriaService.listarCategorias();
        cmbCategoria.setItems(FXCollections.observableList(categorias));
    }

    public void cargarDisponibilidad() {
        cmbDisponibilidad.setItems(FXCollections.observableArrayList(true, false)); 
    }

    public void cargarDatosEditar() {
        Libro libro = tblLibro.getSelectionModel().getSelectedItem();
        if (libro != null) {
            tfID.setText(Long.toString(libro.getId()));
            tfNombre.setText(libro.getNombre());
            taSinopsis.setText(libro.getSinopsis());
            tfAutor.setText(libro.getAutor());
            tfEditorial.setText(libro.getEditorial());
            tfEstanteria.setText(libro.getNumeroEstanteria());
            tfCluster.setText(libro.getCluster());
            tfIsbn.setText(libro.getIsbn()); 
            cmbCategoria.setValue(libro.getCategoria());
            cmbDisponibilidad.setValue(libro.getDisponibilidad());
        }
    }

    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btnGuardar) {
            if (!tfID.getText().isBlank()) {
                editarLibro();
            } else {
                agregarLibro();
            }
        } else if (event.getSource() == btnLimpiar) {
            limpiarForm();
        } else if (event.getSource() == btnEliminar) {
            eliminarLibro();
        } else if (event.getSource() == btnBuscar) {
            buscarLibro();
        }
    }

    public void limpiarForm() {
        tfID.clear();
        tfNombre.clear();
        tfAutor.clear();
        tfEditorial.clear();
        tfEstanteria.clear();
        tfCluster.clear();
        tfIsbn.clear(); 
        taSinopsis.clear();
        cmbCategoria.setValue(null);
        cmbDisponibilidad.setValue(true); 
    }

    public ObservableList<Libro> listarLibro() {
        return FXCollections.observableList(libroService.listarLibro());
    }

    public void agregarLibro() {
        Libro libro = new Libro();
        libro.setNombre(tfNombre.getText());
        libro.setSinopsis(taSinopsis.getText());
        libro.setAutor(tfAutor.getText());
        libro.setEditorial(tfEditorial.getText());
        libro.setNumeroEstanteria(tfEstanteria.getText());
        libro.setCluster(tfCluster.getText());
        libro.setIsbn(tfIsbn.getText()); 
        libro.setCategoria(cmbCategoria.getValue());
        libro.setDisponibilidad(cmbDisponibilidad.getValue()); 
        libroService.guardarLibro(libro);
        cargarDatos();
    }

    public void editarLibro() {
        Libro libro = libroService.buscarLibroPorId(Long.parseLong(tfID.getText()));
        libro.setNombre(tfNombre.getText());
        libro.setSinopsis(taSinopsis.getText());
        libro.setAutor(tfAutor.getText());
        libro.setEditorial(tfEditorial.getText());
        libro.setNumeroEstanteria(tfEstanteria.getText());
        libro.setCluster(tfCluster.getText());
        libro.setIsbn(tfIsbn.getText()); 
        libro.setCategoria(cmbCategoria.getValue());
        libro.setDisponibilidad(cmbDisponibilidad.getValue()); 
        libroService.guardarLibro(libro);
        cargarDatos();
    }

    public void eliminarLibro() {
        Libro libro = libroService.buscarLibroPorId(Long.parseLong(tfID.getText()));
        libroService.eliminarLibro(libro);
        cargarDatos();
    }

    public void buscarLibro() {
        String buscarId = tfBuscar.getText();
        if (!buscarId.isBlank()) {
            try {
                Long id = Long.parseLong(buscarId);
                Libro libro = libroService.buscarLibroPorId(id);
                tblLibro.setItems(FXCollections.observableArrayList(libro));
            } catch (Exception e) {
                // TODO: handle exception
            }
        } else {
            cargarDatos();
        }
    }
}
