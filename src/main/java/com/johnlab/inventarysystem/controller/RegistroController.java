package main.java.com.johnlab.inventarysystem.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.SQLException;
import javafx.fxml.FXML;
import main.java.com.johnlab.inventarysystem.model.Usuario;
import main.java.com.johnlab.inventarysystem.service.UsuarioService;
import main.java.com.johnlab.inventarysystem.utils.SceneManager;
import org.mindrot.jbcrypt.BCrypt;
import static org.mindrot.jbcrypt.BCrypt.gensalt;

public class RegistroController implements Initializable {

    @FXML
    private TextField txtRol;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    @FXML
    private PasswordField psContrasena;

    private SceneManager sceneManager;
    private UsuarioService usuarioService;

    public RegistroController(SceneManager sceneManager, UsuarioService usuarioService) {
        this.sceneManager = sceneManager;
        this.usuarioService = usuarioService;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleRegistrarUsuario() throws Exception {
        if (validarCampos()) {

            String rol = txtRol.getText().trim();
            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String correo = txtCorreo.getText().trim();
            String contrasena = psContrasena.getText().trim();

            String contrasena_hash = BCrypt.hashpw(contrasena, gensalt(12));

            Usuario usuario = new Usuario(rol, nombre, apellido, correo, contrasena_hash);

            try {
                if (usuarioService.registrarUsuario(usuario)) {
                    sceneManager.showInfoAlert("Éxito", "Usuario creado", "El usuario se ha creado correctamente", Alert.AlertType.INFORMATION);
                    sceneManager.showLoginView();

                }
            } catch (SQLException e) {
                System.out.println("Error al registrar usuario: " + e.getMessage());
            }

        }
    }

    @FXML
    private void handleCancelar() throws Exception {
        sceneManager.showLoginView();
    }

    private boolean validarCampos() {

        if (txtRol.getText().trim().isBlank() || txtNombre.getText().trim().isBlank() || txtApellido.getText().trim().isBlank() || txtCorreo.getText().trim().isBlank() || psContrasena.getText().trim().isBlank()) {
            sceneManager.showInfoAlert("Advertencia", "Campos faltantes", "Ingrese todos los campos requeridos", Alert.AlertType.WARNING);
            return false;
        } else {
            if (validarRol()) {
                return true;
            } else {
                return false;
            }

        }

    }

    private boolean validarRol() {
        if (txtRol.getText().trim().equals("1") || txtRol.getText().trim().equals("2")) {
            return true;
        } else {
            sceneManager.showInfoAlert("Error de validación", "Rol inválido", "Ingrese 1 para Usuario o 2 para Administrador", Alert.AlertType.WARNING);
            return false;
        }
    }

}
