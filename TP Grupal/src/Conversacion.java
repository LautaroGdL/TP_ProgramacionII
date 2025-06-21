import java.util.ArrayList;
import java.util.List;

public class Conversacion {
    private Usuario usuario1;
    private Usuario usuario2;
    private List<String> mensajes;

    public Conversacion(Usuario usuario1, Usuario usuario2) {
        this.usuario1 = usuario1;
        this.usuario2 = usuario2;
        this.mensajes = new ArrayList<>();
    }

    public void agregarMensaje(String emisor, String contenido) {
        mensajes.add(emisor + ": " + contenido);
    }

    public List<String> obtenerMensajes() {
        return mensajes;
    }

    public boolean esEntre(Usuario u1, Usuario u2) {
        return (u1.equals(usuario1) && u2.equals(usuario2)) ||
                (u1.equals(usuario2) && u2.equals(usuario1));
    }
}
