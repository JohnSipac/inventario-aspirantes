package main.java.com.johnlab.inventarysystem.service;

import java.sql.SQLException;
import main.java.com.johnlab.inventarysystem.model.Usuario;
import main.java.com.johnlab.inventarysystem.repository.UsuarioRepository;

public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public boolean registrarUsuario(Usuario usuario) throws SQLException {
        if (usuarioRepository.registerUsuario(usuario)) {
            return true;
        } else {
            return false;
        }
    }

    public Usuario encontrarUsuarioPorEmail(Usuario usuario) throws SQLException {
        Usuario user = usuarioRepository.findUserbyEmail(usuario);
        return user;
    }
}
