import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int id;
    private String nombre;
    private List<Usuario> amigos;

    public Usuario(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
        this.amigos = new ArrayList<>();
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
        if (!amigos.contains(otro)) {
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

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre;
    }

    public void mostrarAmigos() {
        if (amigos.isEmpty()) {
            System.out.println(nombre + " no tiene amigos.");
        } else {
            System.out.println("Amigos de " + nombre + ":");
            for (Usuario amigo : amigos) {
                System.out.println(" - " + amigo.getNombre());
            }
        }
    }
}
