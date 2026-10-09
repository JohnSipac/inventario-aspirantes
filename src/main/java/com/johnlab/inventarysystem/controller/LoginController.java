package main.java.com.johnlab.inventarysystem.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.java.com.johnlab.inventarysystem.model.Usuario;
import main.java.com.johnlab.inventarysystem.service.UsuarioService;
import main.java.com.johnlab.inventarysystem.utils.SceneManager;
import org.mindrot.jbcrypt.BCrypt;

public class LoginController implements Initializable {

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField psContrasena;

    private SceneManager sceneManager;
    private UsuarioService usuarioService;

    public LoginController(SceneManager sceneManager, UsuarioService usuarioService) {
        this.sceneManager = sceneManager;
        this.usuarioService = usuarioService;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    @FXML
    private void handleIngresar() throws Exception {
        if (validarCampos()) {
            String correo = txtCorreo.getText().trim();
            String contrasena = psContrasena.getText().trim();

            Usuario ingresado = new Usuario(correo, contrasena);
            try {
                Usuario encontrado = usuarioService.encontrarUsuarioPorEmail(ingresado);

                if (encontrado != null) {

                    Boolean contrasenaConfirmada = BCrypt.checkpw(contrasena, encontrado.getContrasena());

                    if (contrasenaConfirmada) {
                        sceneManager.showInventarioView();
                    } else {
                        sceneManager.showInfoAlert("Advertencia", "Datos incorrectos", "Correo o contraseña incorrectos", Alert.AlertType.WARNING);

                    }

                } else {
                    sceneManager.showInfoAlert("Advertencia", "Datos incorrectos", "Correo o contraseña incorrectos", Alert.AlertType.WARNING);
                }
            } catch (SQLException e) {
                System.out.println("Error al encontrar usuario: " + e.getMessage());
            }

        }
    }

    private boolean validarCampos() {
        if (txtCorreo.getText().trim().isBlank() || psContrasena.getText().trim().isBlank()) {
            sceneManager.showInfoAlert("Advertencia", "Campos vacíos", "Llene todos los campos requeridos", Alert.AlertType.INFORMATION);
            return false;
        } else {
            return true;
        }
    }
    
    @FXML
    private void handleRegistrarse()throws Exception{
        sceneManager.showRegistroView();
    }
    
}
