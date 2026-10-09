package main.java.com.johnlab.inventarysystem.model;

public class Usuario {
    
    private int idUsuario;
    private String id_rol;
    private String nombre;
    private String apellido;
    private String email;
    private String contrasena;

    public Usuario(int idUsuario, String id_rol, String nombre, String apellido, String email, String contrasena) {
        this.idUsuario = idUsuario;
        this.id_rol = id_rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Usuario(String id_rol, String nombre, String apellido, String email, String contrasena) {
        this.id_rol = id_rol;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.contrasena = contrasena;
    }

    public Usuario(String email, String contrasena) {
        this.email = email;
        this.contrasena = contrasena;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id_usuario) {
        this.idUsuario = id_usuario;
    }

    public String getIdRol() {
        return id_rol;
    }

    public void setIdRol(String id_rol) {
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
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
