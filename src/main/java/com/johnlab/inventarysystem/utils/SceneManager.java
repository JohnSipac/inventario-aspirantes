package main.java.com.johnlab.inventarysystem.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import main.java.com.johnlab.inventarysystem.controller.InventarioController;
import main.java.com.johnlab.inventarysystem.controller.LoginController;
import main.java.com.johnlab.inventarysystem.controller.RegistroController;
import main.java.com.johnlab.inventarysystem.model.Aspirante;
import main.java.com.johnlab.inventarysystem.repository.AspiranteRepository;
import main.java.com.johnlab.inventarysystem.repository.UsuarioRepository;
import main.java.com.johnlab.inventarysystem.service.InventarioService;
import main.java.com.johnlab.inventarysystem.service.UsuarioService;

public class SceneManager {

    private Stage primaryStage;

    public SceneManager(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void showRegistroView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/registro.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == RegistroController.class) {
                UsuarioRepository usuarioRepository = new UsuarioRepository();
                UsuarioService usuarioService = new UsuarioService(usuarioRepository);

                return new RegistroController(this, usuarioService);
            }

            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error al cargar el constructor:" + e.getMessage());
            }
        });

        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void showLoginView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/login.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == LoginController.class) {
                UsuarioRepository usuarioRepository = new UsuarioRepository();
                UsuarioService usuarioService = new UsuarioService(usuarioRepository);

                return new LoginController(this, usuarioService);
            }

            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error al cargar el constructor:" + e.getMessage());
            }
        });

        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void showInventarioView() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/main/resources/view/inventario.fxml"));

        loader.setControllerFactory(clazz -> {
            if (clazz == InventarioController.class) {
                AspiranteRepository aspiranteRepository = new AspiranteRepository();
                InventarioService inventarioService = new InventarioService(aspiranteRepository);
                Aspirante aspirante = new Aspirante();
                return new InventarioController(this, inventarioService, aspirante);
            }

            try {
                return clazz.getDeclaredConstructor().newInstance();
            } catch (Exception e) {
                throw new RuntimeException("Error al cargar el constructor:" + e.getMessage());
            }
        });

        Parent root = loader.load();
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    public void showInfoAlert(String title, String header, String content, AlertType type) {
        Alert alerta = new Alert(type);
        alerta.initOwner(primaryStage);
        alerta.setTitle(title);
        alerta.setHeaderText(header);
        alerta.setContentText(content);

        alerta.showAndWait();
    }

}
