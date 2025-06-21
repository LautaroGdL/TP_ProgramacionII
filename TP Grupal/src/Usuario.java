import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Usuario implements ConjuntoUsuario {
    private final int id;
    private final String nombre;
    private List<Usuario> amigos;
    private List<Mensaje> bandejaEntrada;

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.amigos = new ArrayList<>();
        this.bandejaEntrada = new ArrayList<>();
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

    public void agregarAmigo(Usuario otro) {
        if (!amigos.contains(otro) && otro != this) {
            amigos.add(otro);
            otro.agregarAmigo(this); // doble enlace
        }
    }

    public void eliminarAmigo(Usuario otro) {
        if (amigos.contains(otro)) {
            amigos.remove(otro);
            otro.eliminarAmigo(this); // romper el doble enlace
        }
    }

    public void mostrarAmigos() {
        if (amigos.isEmpty()) {
            System.out.println(nombre + " no tiene amigos.");
        } else {
            System.out.println("Amigos de " + nombre + ":");
            for (Usuario amigo : amigos) {
                System.out.println(" - " + amigo.getNombre() + " (ID: " + amigo.getId() + ")");
            }
        }
    }

    public boolean estaConectadoCon(Usuario destino) {
        List<Usuario> visitados = new ArrayList<>();
        Queue<Usuario> cola = new LinkedList<>();
        cola.add(this);

        while (!cola.isEmpty()) {
            Usuario actual = cola.poll();
            if (actual.equals(destino)) return true;

            if (!visitados.contains(actual)) {
                visitados.add(actual);
                for (Usuario amigo : actual.getAmigos()) {
                    if (!visitados.contains(amigo)) {
                        cola.add(amigo);
                    }
                }
            }
        }

        return false;
    }

    public void recibirMensaje(Mensaje mensaje) {
        bandejaEntrada.add(mensaje);
    }

    public void mostrarMensajes() {
        if (bandejaEntrada.isEmpty()) {
            System.out.println("No tienes mensajes.");
        } else {
            System.out.println("Mensajes recibidos:");
            for (Mensaje mensaje : bandejaEntrada) {
                System.out.println(mensaje);
            }
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre;
    }

    // Métodos vacíos de la interfaz (no usados)
    @Override public void inicializar() {}
    @Override public void agregar(int valor) {}
    @Override public boolean pertenece(int valor) { return false; }
    @Override public void sacar(int valor) {}
    @Override public int elegir() { return 0; }
    @Override public boolean estaVacia() { return false; }
}
