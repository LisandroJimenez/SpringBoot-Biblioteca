package com.lisandrojimenez.webapp.Biblioteca.system;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.lisandrojimenez.webapp.Biblioteca.BibliotecaApplication;
import com.lisandrojimenez.webapp.Biblioteca.controller.FXController.CategoriaViewController;
import com.lisandrojimenez.webapp.Biblioteca.controller.FXController.ClienteViewController;
import com.lisandrojimenez.webapp.Biblioteca.controller.FXController.EmpleadoViewController;
import com.lisandrojimenez.webapp.Biblioteca.controller.FXController.IndexController;
import com.lisandrojimenez.webapp.Biblioteca.controller.FXController.LibroViewController;
import com.lisandrojimenez.webapp.Biblioteca.controller.FXController.PrestamoViewController;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application{
    private ConfigurableApplicationContext applicationContext;
    private Stage stage;
    private Scene scene;

    @Override
    public void init(){
        this.applicationContext = new SpringApplicationBuilder(BibliotecaApplication.class).run();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.stage= primaryStage;
        stage.setTitle("Biblioteca");
        indexView();
        stage.show();
    }

    public Initializable switchScene(String fxmlName, int width, int height)throws IOException{
        Initializable resultado =  null;
        FXMLLoader loader = new FXMLLoader();
        loader.setControllerFactory(applicationContext::getBean);
        InputStream archivo = Main.class.getResourceAsStream("/templates/" + fxmlName);
        loader.setBuilderFactory(new JavaFXBuilderFactory());
        loader.setLocation(Main.class.getResource("/templates/" + fxmlName));
        
        scene = new Scene((AnchorPane) loader.load(archivo), width, height);
        stage.setScene(scene);
        stage.sizeToScene();
        resultado = (Initializable)loader.getController();
        return resultado;
    }

    public void indexView(){
        try {
            IndexController indexView = (IndexController)switchScene("index.fxml", 1000, 600);
            indexView.setStage(this);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void categoriaView(){
        try {
            CategoriaViewController categoriaView = (CategoriaViewController)switchScene("categoria.fxml", 1000, 600);
            categoriaView.setStage(this);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void clienteView(){
        try {
            ClienteViewController clienteView = (ClienteViewController)switchScene("cliente.fxml", 1000, 600);
            clienteView.setStage(this);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void empleadoView(){
        try {
            EmpleadoViewController empleadoView = (EmpleadoViewController)switchScene("empleado.fxml", 1000, 600);
            empleadoView.setStage(this);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public void libroView(){
        try {
            LibroViewController libroView = (LibroViewController)switchScene("libro.fxml", 1300, 700);
            libroView.setStage(this);
        } catch (Exception e) { 
            // TODO: handle exception
        }
    }

    public void prestamoView(){
        try {
            PrestamoViewController prestamoView = (PrestamoViewController)switchScene("prestamo.fxml", 1000, 600);
            prestamoView.setStage(this);
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
    
}
