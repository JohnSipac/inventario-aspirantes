package main.java.com.johnlab.inventarysystem.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import main.java.com.johnlab.inventarysystem.model.Aspirante;
import main.java.com.johnlab.inventarysystem.service.InventarioService;
import main.java.com.johnlab.inventarysystem.utils.SceneManager;

public class InventarioController implements Initializable {

    @FXML
    private TableView<Aspirante> tvAspirantes;

    @FXML
    private TableColumn<Aspirante, String> tcIdAspirante;

    @FXML
    private TableColumn<Aspirante, String> tcNombre;

    @FXML
    private TableColumn<Aspirante, String> tcApellido;

    @FXML
    private TableColumn<Aspirante, String> tcCorreo;

    @FXML
    private TableColumn<Aspirante, String> tcTelefono;

    @FXML
    private TableColumn<Aspirante, String> tcDireccion;

    @FXML
    private TableColumn<Aspirante, String> tcEdad;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtEdad;

    @FXML
    private VBox panelDatos;

    private SceneManager sceneManager;
    private InventarioService inventarioService;
    private Aspirante seleccionado;

    public InventarioController(SceneManager sceneManager, InventarioService inventarioService, Aspirante seleccionado) {
        this.sceneManager = sceneManager;
        this.inventarioService = inventarioService;
        this.seleccionado = seleccionado;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializarTabla();
        cargarTabla();
    }

    @FXML
    private void guardar() {
        if (validarCampos()) {

            String nombre = txtNombre.getText().trim();
            String apellido = txtApellido.getText().trim();
            String correo = txtCorreo.getText().trim();
            String telefono = txtTelefono.getText().trim();
            String direccion = txtDireccion.getText().trim();

            int edad = Integer.parseInt(txtEdad.getText().trim());

            if (seleccionado == null) {
                try {

                    Aspirante aspirante = new Aspirante(nombre, apellido, correo, telefono, direccion, edad);

                    if (inventarioService.crearAspirante(aspirante)) {
                        sceneManager.showInfoAlert("Éxito", "Aspirante creado", "El aspirante ha sido creado correctamente", Alert.AlertType.WARNING);
                        ocultarPanel();
                        limpiarCampos();
                        cargarTabla();
                    }

                } catch (Exception e) {
                    System.out.println("Error al crear aspirante: " + e.getMessage());
                    if (e.getMessage().contains("Duplicate entry")) {
                        sceneManager.showInfoAlert("Advertencia", "El correo ya existe", "Ingrese un correo nuevo", AlertType.INFORMATION);
                    }
                }
            } else {
                try {

                    Aspirante aspirante = new Aspirante(seleccionado.getIdAspirante(), nombre, apellido, correo, telefono, direccion, edad);

                    if (inventarioService.actualizarAspirante(aspirante)) {
                        sceneManager.showInfoAlert("Éxito", "Aspirante actualizado", "El aspirante ha sido actualizado correctamente", Alert.AlertType.WARNING);
                        ocultarPanel();
                        limpiarCampos();
                        cargarTabla();
                    }

                } catch (Exception e) {
                    System.out.println("Error al actualizar aspirante: " + e.getMessage());
                }
            }
        }
    }

    @FXML
    private void handleEliminarAspirante() {
        seleccionado = tvAspirantes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            sceneManager.showInfoAlert("Advertencia", "Contenido no seleccionado", "Seleccione el aspirante a eliminar", Alert.AlertType.WARNING);
        } else {

            Alert alerta = new Alert(AlertType.CONFIRMATION);
            alerta.setTitle("Confirmación");
            alerta.setHeaderText("Confirme eliminación");
            alerta.setContentText("¿Está seguro de eliminar el aspirante?");

            Optional<ButtonType> result = alerta.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                if (inventarioService.eliminarAspirante(seleccionado)) {
                    cargarTabla();
                    sceneManager.showInfoAlert("Éxito", "Aspirante eliminado", "El aspirante se ha eliminado correctamente", Alert.AlertType.INFORMATION);
                } else {
                    System.out.println("Error al eliminar aspirante...");
                }
            }

        }
    }

    @FXML
    private void handleCrearAspirante() {
        seleccionado = null;
        mostrarPanel();
    }

    @FXML
    private void handleActualizarAspirante() {
        seleccionado = tvAspirantes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            sceneManager.showInfoAlert("Advertencia", "Contenido no seleccionado", "Seleccione el aspirante a actualizar", Alert.AlertType.WARNING);
        } else {
            txtNombre.setText(seleccionado.getNombre());
            txtApellido.setText(seleccionado.getApellido());
            txtCorreo.setText(seleccionado.getCorreo());
            txtTelefono.setText(seleccionado.getTelefono());
            txtDireccion.setText(seleccionado.getDireccion());
            txtEdad.setText(String.valueOf(seleccionado.getEdad()));

            mostrarPanel();
        }
    }

    private void inicializarTabla() {
        tcIdAspirante.setCellValueFactory(new PropertyValueFactory("idAspirante"));
        tcNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
        tcApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
        tcCorreo.setCellValueFactory(new PropertyValueFactory("correo"));
        tcTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
        tcDireccion.setCellValueFactory(new PropertyValueFactory("direccion"));
        tcEdad.setCellValueFactory(new PropertyValueFactory("edad"));
    }

    private void cargarTabla() {
        ObservableList lista = inventarioService.listarAspirantes();
        tvAspirantes.setItems(lista);
    }

    private boolean validarCampos() {
        if (txtNombre.getText().trim().isBlank() || txtApellido.getText().trim().isBlank() || txtCorreo.getText().trim().isBlank() || txtTelefono.getText().trim().isBlank() || txtDireccion.getText().trim().isBlank() || txtEdad.getText().trim().isBlank()) {
            sceneManager.showInfoAlert("Advertencia", "Campos vacíos", "Llene todos los campos correspondientes", Alert.AlertType.WARNING);
            return false;
        } else {
            if (validarEdad()) {
                return true;
            } else {
                return false;
            }
        }
    }

    @FXML
    private void handleCancel() {
        ocultarPanel();
        limpiarCampos();
    }

    private boolean validarEdad() {

        try {
            int valorEntero = Integer.parseInt(txtEdad.getText().trim());
            return true;
        } catch (Exception e) {
            sceneManager.showInfoAlert("Error de validación", "Edad no válida", "En edad  ingrese un valor numérico", Alert.AlertType.WARNING);
            return false;
        }

    }

    private void mostrarPanel() {
        panelDatos.setVisible(true);
    }

    private void ocultarPanel() {
        panelDatos.setVisible(false);
    }

    private void limpiarCampos() {
        txtNombre.clear();
        txtApellido.clear();
        txtCorreo.clear();
        txtTelefono.clear();
        txtDireccion.clear();
        txtEdad.clear();
    }

    @FXML
    private void handleCerrarSesion() throws Exception {
        sceneManager.showLoginView();
    }

}
