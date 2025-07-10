package Modelos;

import Interfaces.UsuarioInterface;

import java.util.*;

public  class Usuario implements UsuarioInterface {
    private int id;
    private String nombre;
    private String username;
    private String password;

    private Set<Usuario> amigos;
    private Map<Usuario, List<String>> mensajes;
    private List<Mensaje> bandeja;

    public Usuario(int id, String nombre, String username, String password) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.password = password;
        this.amigos = new HashSet<>();
        this.mensajes = new HashMap<>();
        this.bandeja = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<Usuario> getAmigos() {
        return amigos;
    }

    public void agregarAmigo(Usuario amigo) {
        if (!amigo.equals(this) && amigos.add(amigo)) {
            amigo.agregarAmigo(this);
        }
    }

    public void eliminarAmigo(Usuario amigo, List<Usuario> bajas) {
        if (amigos.remove(amigo)) {
            amigo.getAmigos().remove(this);
            bajas.add(amigo);
        }
    }

    @Override
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

    @Override
    public void enviarMensaje(Usuario destino, String contenido) {
        mensajes.putIfAbsent(destino, new ArrayList<>());
        destino.mensajes.putIfAbsent(this, new ArrayList<>());

        mensajes.get(destino).add("Yo: " + contenido);
        destino.mensajes.get(this).add(this.nombre + ": " + contenido);
    }

    @Override
    public void verMensajesCon(Usuario otro) {
        List<String> historial = mensajes.get(otro);
        if (historial == null || historial.isEmpty()) {
            System.out.println("No hay mensajes con " + otro.getNombre());
        } else {
            System.out.println("Conversación con " + otro.getNombre() + ":");
            for (String m : historial) {
                System.out.println(m);
            }
        }
    }

    @Override
    public boolean estaConectadoCon(Usuario destino) {
        Set<Usuario> visitados = new HashSet<>();
        Queue<Usuario> cola = new LinkedList<>();
        cola.add(this);

        while (!cola.isEmpty()) {
            Usuario actual = cola.poll();
            if (actual.equals(destino)) return true;

            if (visitados.add(actual)) {
                cola.addAll(actual.getAmigos());
            }
        }

        return false;
    }

    @Override
    public boolean esAmigoDe(Usuario otro) {
        return amigos.contains(otro);
    }

    @Override
    public void recibirMensaje(Mensaje mensaje) {
        bandeja.add(mensaje);
    }

    @Override
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
        return "ID: " + id + " | Nombre: " + nombre + " | Usuario: " + username;
    }
}
