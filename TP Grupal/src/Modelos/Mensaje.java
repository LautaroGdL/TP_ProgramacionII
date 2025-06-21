package Modelos;

// Clase Mensaje
public class Mensaje {
    private Usuario emisor;
    private Usuario receptor;
    private String contenido;

    public Mensaje(Usuario emisor, Usuario receptor, String contenido) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.contenido = contenido;
    }

    public String toString() {
        return emisor.getNombre() + " -> " + receptor.getNombre() + ": " + contenido;
    }
}