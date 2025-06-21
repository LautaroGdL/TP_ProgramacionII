package Modelos;

import java.util.*;

public class GrafoUsuarios {
    private List<Usuario> usuarios;

    public GrafoUsuarios() {
        usuarios = new ArrayList<>();
    }

    public void agregarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public void conectarUsuarios(Usuario a, Usuario b) {
        a.agregarAmigo(b);
    }

    public boolean estanConectados(Usuario a, Usuario b) {
        return a.estaConectadoCon(b);
    }

    public List<Usuario> obtenerUsuarios() {
        return usuarios;
    }
}