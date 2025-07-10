package Interfaces;
import Modelos.*;
import java.util.*;

public interface UsuarioInterface {
    void mostrarAmigos();
    void enviarMensaje(Usuario destino, String contenido);
    void verMensajesCon(Usuario otro);
    boolean estaConectadoCon(Usuario destino);
    boolean esAmigoDe(Usuario otro);
    void recibirMensaje(Mensaje mensaje);
    void mostrarMensajes();
    int getId();
    String getNombre();
    Set<Usuario> getAmigos();

}