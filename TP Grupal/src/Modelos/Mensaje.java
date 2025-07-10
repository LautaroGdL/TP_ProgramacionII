package Modelos;

public class Mensaje {
    private final String contenido;

    public Mensaje(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return contenido;
    }
}
