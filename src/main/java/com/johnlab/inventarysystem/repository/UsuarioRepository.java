package main.java.com.johnlab.inventarysystem.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import main.java.com.johnlab.inventarysystem.config.ConnectionDb;
import main.java.com.johnlab.inventarysystem.model.Usuario;

public class UsuarioRepository {
    
    public boolean registerUsuario(Usuario usuario) throws SQLException {
        String sql = "insert into usuarios (id_rol, nombre, apellido, email, contrasena_hash) "
                + "values (?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = ConnectionDb.getConnectionDb().prepareStatement(sql)) {

            pstm.setString(1, usuario.getIdRol());
            pstm.setString(2, usuario.getNombre());
            pstm.setString(3, usuario.getApellido());
            pstm.setString(4, usuario.getEmail());
            pstm.setString(5, usuario.getContrasena());

            return pstm.executeUpdate() > 0;
        }
    }

    public Usuario findUserbyEmail(Usuario usuario) throws SQLException {
        String sql = "select id_rol, nombre, apellido, email, contrasena_hash "
                + "from usuarios where email = ?";

        try (PreparedStatement pstm = ConnectionDb.getConnectionDb().prepareStatement(sql)) {

            pstm.setString(1, usuario.getEmail());

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    return new Usuario(
                            rs.getString("id_rol"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("contrasena_hash")
                    );
                }
            }
        }
        return null;

    }
    
}
