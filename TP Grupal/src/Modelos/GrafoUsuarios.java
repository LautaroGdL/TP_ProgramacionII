package Modelos;

import java.util.*;

public class GrafoUsuarios {
    private Map<Usuario, Set<Usuario>> grafo;

    public GrafoUsuarios() {
        grafo = new HashMap<>();
    }

    public void agregarUsuario(Usuario u) {
        grafo.putIfAbsent(u, new HashSet<>());
    }

    public void agregarRelacion(Usuario a, Usuario b) {
        grafo.get(a).add(b);
        grafo.get(b).add(a);
    }

    public Set<Usuario> sugerenciasDeAmigos(Usuario u) {
        Set<Usuario> sugerencias = new HashSet<>();
        for (Usuario amigo : u.getAmigos()) {
            for (Usuario posible : amigo.getAmigos()) {
                if (!u.equals(posible) && !u.getAmigos().contains(posible)) {
                    sugerencias.add(posible);
                }
            }
        }
        return sugerencias;
    }
}
