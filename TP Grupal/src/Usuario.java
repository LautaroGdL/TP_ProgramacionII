import java.util.ArrayList;
import java.util.List;

public class Usuario implements ConjuntoUsuario{
    private final int id;
    private final String nombre;
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

    @Override
    public String toString() {
        return "ID: " + id + " | Nombre: " + nombre;
    }


    @Override
    public void inicializar() {}

    @Override
    public void agregar(int valor) {}

    @Override
    public boolean pertenece(int valor) {
        return false;
    }

    @Override
    public void sacar(int valor) {}

    @Override
    public int elegir() {
        return 0;
    }

    @Override
    public boolean estaVacia() {
        return false;
    }
}
