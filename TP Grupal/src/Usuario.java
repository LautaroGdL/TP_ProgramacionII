import java.util.*;

public class Usuario {
    private String nombre;
    private List<Usuario> amigos;
    private Map<Usuario, List<String>> mensajes;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.amigos = new ArrayList<>();
        this.mensajes = new HashMap<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<Usuario> getAmigos() {
        return amigos;
    }

    public void agregarAmigo(Usuario amigo) {
        if (!amigos.contains(amigo) && amigo != this) {
            amigos.add(amigo);
            amigo.agregarAmigo(this);
        }
    }

    public void eliminarAmigo(Usuario amigo) {
        if (amigos.contains(amigo)) {
            amigos.remove(amigo);
            amigo.getAmigos().remove(this);
        }
    }

    public void enviarMensaje(Usuario destino, String contenido) {
        mensajes.putIfAbsent(destino, new ArrayList<>());
        destino.mensajes.putIfAbsent(this, new ArrayList<>());

        String mensajeEnviado = "Yo: " + contenido;
        String mensajeRecibido = this.nombre + ": " + contenido;

        mensajes.get(destino).add(mensajeEnviado);
        destino.mensajes.get(this).add(mensajeRecibido);
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
        if (this == destino) return true;
        Queue<Usuario> cola = new LinkedList<>();
        Set<Usuario> visitados = new HashSet<>();
        cola.add(this);
        visitados.add(this);

        while (!cola.isEmpty()) {
            Usuario actual = cola.poll();
            for (Usuario amigo : actual.getAmigos()) {
                if (amigo.equals(destino)) {
                    return true;
                }
                if (!visitados.contains(amigo)) {
                    visitados.add(amigo);
                    cola.add(amigo);
                }
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
