import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ConjuntoImplement implements ConjuntoUsuario {
    private Set<Integer> conjunto;

    @Override
    public void inicializar() {
        conjunto = new HashSet<>();
    }

    @Override
    public void agregar(int valor) {
        conjunto.add(valor);
    }

    @Override
    public boolean pertenece(int valor) {
        return conjunto.contains(valor);
    }

    @Override
    public void sacar(int valor) {
        conjunto.remove(valor);
    }

    @Override
    public int elegir() {
        if (!conjunto.isEmpty()) {
            Iterator<Integer> it = conjunto.iterator();
            return it.next();
        }
        return -1; // o lanza una excepción
    }

    @Override
    public boolean estaVacia() {
        return conjunto.isEmpty();
    }

    public Set<Integer> obtenerTodos() {
        return conjunto;
    }
}
