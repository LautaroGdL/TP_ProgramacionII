package Modelos;

import java.util.*;

public class GrafoGrupo {
    private String nombre;
    private Set<Usuario> miembros;
    private List<String> mensajes;
    private Map<Usuario, Set<Usuario>> grafoConversaciones;

    public GrafoGrupo(String nombre) {
        this.nombre = nombre;
        this.miembros = new HashSet<>();
        this.mensajes = new ArrayList<>();
        this.grafoConversaciones = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public boolean esMiembro(Usuario u) {
        return miembros.contains(u);
    }

    public void agregarMiembro(Usuario u) {
        if (miembros.add(u)) {
            grafoConversaciones.putIfAbsent(u, new HashSet<>());
        }
    }

    public void eliminarMiembro(Usuario u) {
        if (miembros.remove(u)) {
            grafoConversaciones.remove(u);
            for (Set<Usuario> vecinos : grafoConversaciones.values()) {
                vecinos.remove(u);
            }
            System.out.println(u.getNombre() + " fue eliminado del grupo.");
        }
    }

    public void enviarMensaje(Usuario origen, Usuario destino, String contenido) {
        if (esMiembro(origen) && esMiembro(destino)) {
            mensajes.add(origen.getNombre() + " → " + destino.getNombre() + ": " + contenido);
            grafoConversaciones.get(origen).add(destino);
            grafoConversaciones.get(destino).add(origen);
        }
    }

    public void mostrarMensajes() {
        System.out.println("Mensajes del grupo " + nombre + ":");
        for (String msg : mensajes) {
            System.out.println(msg);
        }
    }

    public void mostrarGrafo() {
        System.out.println("Conexiones en el grupo '" + nombre + "':");
        for (Usuario u : grafoConversaciones.keySet()) {
            System.out.print(u.getNombre() + " conectado con: ");
            for (Usuario v : grafoConversaciones.get(u)) {
                System.out.print(v.getNombre() + " ");
            }
            System.out.println();
        }
    }
}
