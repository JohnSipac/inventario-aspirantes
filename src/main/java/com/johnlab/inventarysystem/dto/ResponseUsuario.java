package main.java.com.johnlab.inventarysystem.dto;

public class ResponseUsuario {

    private int idUsuario;
    private String email;
    private String contrasena;

    public ResponseUsuario(int idUsuario, String email, String contrasena) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.contrasena = contrasena;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    

}
