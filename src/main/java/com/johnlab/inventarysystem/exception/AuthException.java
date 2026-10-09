package main.java.com.johnlab.inventarysystem.exception;

public class AuthException extends RuntimeException {
    
    private final AuthErrorCode codigo;

    public AuthException(AuthErrorCode codigo) {
        this.codigo = codigo;
    }

    public AuthErrorCode getCodigo() {
        return codigo;
    }
    
}
