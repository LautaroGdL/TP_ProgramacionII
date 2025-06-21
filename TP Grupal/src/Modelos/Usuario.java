package Modelos;

import Interfaces.UsuarioInterface;

import java.util.*;

public class Usuario implements UsuarioInterface {
    private int id;
    private String nombre;
    private List<Usuario> amigos;
    private Map<Usuario, List<String>> mensajes;
    private List<Mensaje> bandeja;

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.amigos = new ArrayList<>();
        this.mensajes = new HashMap<>();
        this.bandeja = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void agregarAmigo(Usuario amigo) {
        if (!amigos.contains(amigo)) {
            amigos.add(amigo);
            amigo.agregarAmigo(this);
        }
    }

    public void eliminarAmigo(Usuario amigo) {
        amigos.remove(amigo);
        amigo.getAmigos().remove(this);
    }

    public void mostrarAmigos() {
        if (amigos.isEmpty()) {
            System.out.println("No tienes amigos.");
        } else {
            System.out.println("Tus amigos:");
            for (Usuario a : amigos) {
                System.out.println("- " + a.getNombre() + " (ID: " + a.getId() + ")");
            }
        }
    }

    public void enviarMensaje(Usuario destino, String contenido) {
        mensajes.putIfAbsent(destino, new ArrayList<>());
        destino.mensajes.putIfAbsent(this, new ArrayList<>());
        mensajes.get(destino).add("Yo: " + contenido);
        destino.mensajes.get(this).add(this.nombre + ": " + contenido);
    }

    public void verMensajesCon(Usuario otro) {
        List<String> historial = mensajes.get(otro);
        if (historial == null || historial.isEmpty()) {
            System.out.println("No hay mensajes con " + otro.getNombre());
        } else {
            System.out.println("Conversación con " + otro.getNombre() + ":");
            for (String mensaje : historial) {
                System.out.println(mensaje);
            }
        }
    }

    public boolean estaConectadoCon(Usuario destino) {
        Set<Usuario> visitados = new HashSet<>();
        Queue<Usuario> cola = new LinkedList<>();
        cola.add(this);

        while (!cola.isEmpty()) {
            Usuario actual = cola.poll();
            if (actual.equals(destino)) return true;

            if (!visitados.contains(actual)) {
                visitados.add(actual);
                cola.addAll(actual.getAmigos());
            }
        }

        return false;
    }

    public boolean esAmigoDe(Usuario otro) {
        return amigos.contains(otro);
    }

    public void recibirMensaje(Mensaje mensaje) {
        bandeja.add(mensaje);
    }

    public void mostrarMensajes() {
        if (bandeja.isEmpty()) {
            System.out.println("No tienes mensajes.");
        } else {
            System.out.println("Mensajes recibidos:");
            for (Mensaje m : bandeja) {
                System.out.println(m);
            }
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre;
    }
}
