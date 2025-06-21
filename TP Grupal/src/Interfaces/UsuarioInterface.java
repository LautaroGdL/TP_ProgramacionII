package Interfaces;
import Modelos.*;

public interface UsuarioInterface {
    String getNombre();
    void agregarAmigo(Usuario amigo);
    void eliminarAmigo(Usuario amigo);
    void mostrarAmigos();
    java.util.List<Usuario> getAmigos();
    void enviarMensaje(Usuario destino, String contenido);
    void verMensajesCon(Usuario otro);
    boolean estaConectadoCon(Usuario destino);
    boolean esAmigoDe(Usuario otro);
    void recibirMensaje(Mensaje mensaje);
    void mostrarMensajes();
}