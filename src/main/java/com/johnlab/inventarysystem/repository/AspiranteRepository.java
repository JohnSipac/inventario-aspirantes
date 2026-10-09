package main.java.com.johnlab.inventarysystem.repository;

import javafx.collections.ObservableList;
import main.java.com.johnlab.inventarysystem.config.ConnectionDb;
import main.java.com.johnlab.inventarysystem.model.Aspirante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;

public class AspiranteRepository {

    public ObservableList<Aspirante> findAll() {
        String sql = "select * from aspirantes";

        try (PreparedStatement pstm = ConnectionDb.getConnectionDb().prepareStatement(sql)) {
            ResultSet rs = pstm.executeQuery();

            ObservableList<Aspirante> listaAspirantes = FXCollections.observableArrayList();

            while (rs.next()) {
                listaAspirantes.add(new Aspirante(
                        rs.getInt("id_aspirante"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("correo"),
                        rs.getString("telefono"),
                        rs.getString("direccion"),
                        rs.getInt("edad")
                ));

            }
            return listaAspirantes;

        } catch (SQLException e) {
            System.out.println("Error al listar aspirantes: " + e.getMessage());
        }

        return null;
    }

    public boolean createAspirante(Aspirante aspirante) {

        String sql = "insert into aspirantes (nombre, apellido, correo, telefono, direccion, edad) values (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = ConnectionDb.getConnectionDb().prepareStatement(sql)) {
            pstm.setString(1, aspirante.getNombre());
            pstm.setString(2, aspirante.getApellido());
            pstm.setString(3, aspirante.getCorreo());
            pstm.setString(4, aspirante.getTelefono());
            pstm.setString(5, aspirante.getDireccion());
            pstm.setInt(6, aspirante.getEdad());

            return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al crear aspirante: " + e.getMessage());
            return false;
        }

    }

    public boolean updateAspirante(Aspirante aspirante) {
        String sql = "update aspirantes set nombre = ?, apellido = ?, correo = ?, telefono = ?, direccion = ?, edad = ? where id_aspirante = ?";

        try (PreparedStatement pstm = ConnectionDb.getConnectionDb().prepareStatement(sql)) {
            pstm.setString(1, aspirante.getNombre());
            pstm.setString(2, aspirante.getApellido());
            pstm.setString(3, aspirante.getCorreo());
            pstm.setString(4, aspirante.getTelefono());
            pstm.setString(5, aspirante.getDireccion());
            pstm.setInt(6, aspirante.getEdad());
            pstm.setInt(7, aspirante.getIdAspirante());

            return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar aspirante: " + e.getMessage());
            return false;
        }

    }

    public boolean deleteAspirante(Aspirante aspirante) {
        String sql = "delete from aspirantes where id_aspirante = ?";

        try (PreparedStatement pstm = ConnectionDb.getConnectionDb().prepareStatement(sql)) {
            pstm.setInt(1, aspirante.getIdAspirante());

            return pstm.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar aspirante: " + e.getMessage());
            return false;
        }

    }

}
