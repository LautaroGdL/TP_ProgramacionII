import Modelos.GrafoUsuarios;
import Modelos.Usuario;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GrafoUsuarios red = new GrafoUsuarios();
        Map<Integer, Usuario> mapaUsuarios = new HashMap<>();
        int id = 0;
        int op;

        do {
            System.out.println("1. Crear usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Ver usuarios");
            System.out.println("4. Salir");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    System.out.print("Nombre: ");
                    String nombre = sc.nextLine();
                    Usuario nuevo = new Usuario(id, nombre);
                    mapaUsuarios.put(id, nuevo);
                    red.agregarUsuario(nuevo);
                    System.out.println("Usuario creado con ID: " + id);
                    id++;
                    break;

                case 2:
                    System.out.print("Nombre de usuario: ");
                    String login = sc.nextLine();
                    Usuario actual = null;
                    for (Usuario u : mapaUsuarios.values()) {
                        if (u.getNombre().equalsIgnoreCase(login)) {
                            actual = u;
                            break;
                        }
                    }
                    if (actual != null) menuUsuario(actual, mapaUsuarios, sc);
                    else System.out.println("No encontrado.");
                    break;

                case 3:
                    for (Usuario u : mapaUsuarios.values()) System.out.println(u);
                    break;
            }
        } while (op != 4);
    }

    private static void menuUsuario(Usuario u, Map<Integer, Usuario> mapa, Scanner sc) {
        int op;
        do {
            System.out.println("\nMenú - Usuario: " + u.getNombre());
            System.out.println("1. Ver amigos");
            System.out.println("2. Agregar amigo");
            System.out.println("3. Eliminar amigo");
            System.out.println("4. Enviar mensaje");
            System.out.println("5. Ver mensajes con usuario");
            System.out.println("6. Ver si están conectados");
            System.out.println("7. Ver si son amigos");
            System.out.println("8. Salir");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {
                case 1:
                    u.mostrarAmigos();
                    break;
                case 2:
                    System.out.print("ID del amigo: ");
                    Usuario a = mapa.get(Integer.parseInt(sc.nextLine()));
                    if (a != null) u.agregarAmigo(a);
                    break;
                case 3:
                    System.out.print("ID del amigo a eliminar: ");
                    Usuario e = mapa.get(Integer.parseInt(sc.nextLine()));
                    if (e != null) u.eliminarAmigo(e);
                    break;
                case 4:
                    System.out.print("ID destinatario: ");
                    Usuario d = mapa.get(Integer.parseInt(sc.nextLine()));
                    if (d != null) {
                        System.out.print("Mensaje: ");
                        u.enviarMensaje(d, sc.nextLine());
                    }
                    break;
                case 5:
                    System.out.print("ID del usuario: ");
                    Usuario m = mapa.get(Integer.parseInt(sc.nextLine()));
                    if (m != null) u.verMensajesCon(m);
                    break;
                case 6:
                    System.out.print("ID del usuario: ");
                    Usuario c = mapa.get(Integer.parseInt(sc.nextLine()));
                    System.out.println(u.estaConectadoCon(c) ? "Conectados" : "No conectados");
                    break;
                case 7:
                    System.out.print("ID del usuario: ");
                    Usuario amigo = mapa.get(Integer.parseInt(sc.nextLine()));
                    System.out.println(u.esAmigoDe(amigo) ? "Sí son amigos" : "No son amigos");
                    break;
            }
        } while (op != 8);
    }
}
