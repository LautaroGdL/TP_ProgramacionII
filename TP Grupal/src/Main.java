import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, Usuario> usuarios = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        int contadorId = 0;

        do {
            System.out.println("\n----- Menú Principal -----");
            System.out.println("1. Crear usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Ver todos los usuarios");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            try {
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número del 1 al 4.");
                opcion = -1;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del nuevo usuario: ");
                    String nombre = scanner.nextLine();
                    Usuario nuevoUsuario = new Usuario(contadorId, nombre);
                    usuarios.put(contadorId, nuevoUsuario);
                    System.out.println("Usuario creado con ID: " + contadorId);
                    contadorId++;
                    break;

                case 2:
                    System.out.print("Ingrese el nombre de usuario: ");
                    String nombreLogin = scanner.nextLine();
                    Usuario usuarioActual = null;
                    for (Usuario u : usuarios.values()) {
                        if (u.getNombre().equalsIgnoreCase(nombreLogin)) {
                            usuarioActual = u;
                            break;
                        }
                    }

                    if (usuarioActual != null) {
                        System.out.println("Sesión iniciada como: " + usuarioActual.getNombre());
                        menuUsuario(usuarioActual, usuarios, scanner);
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;

                case 3:
                    System.out.println("Usuarios registrados:");
                    for (Usuario u : usuarios.values()) {
                        System.out.println(u);
                    }
                    break;

                case 4:
                    System.out.println("Saliendo...");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 4);

        scanner.close();
    }

    private static void menuUsuario(Usuario usuario, HashMap<Integer, Usuario> usuarios, Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n-- Menú de Usuario (" + usuario.getNombre() + ") --");
            System.out.println("1. Ver mis amigos");
            System.out.println("2. Agregar amigo por ID");
            System.out.println("3. Eliminar amigo por ID");
            System.out.println("4. Enviar mensaje");
            System.out.println("5. Ver mensajes");
            System.out.println("6. Ver si estoy conectado con otro usuario");
            System.out.println("7. Cerrar sesión");
            System.out.print("Seleccione una opción: ");
            try {
                System.out.print("Seleccione una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número del 1 al 7.");
                opcion = -1;
            }


            switch (opcion) {
                case 1:
                    usuario.mostrarAmigos();
                    break;

                case 2:
                    System.out.print("Ingrese el ID del amigo a agregar: ");
                    int idAgregar = scanner.nextInt();
                    scanner.nextLine();
                    Usuario amigoAgregar = usuarios.get(idAgregar);
                    if (amigoAgregar != null && amigoAgregar != usuario) {
                        usuario.agregarAmigo(amigoAgregar);
                        System.out.println("Amigo agregado.");
                    } else {
                        System.out.println("ID inválido o no puedes agregarte a ti mismo.");
                    }
                    break;

                case 3:
                    System.out.print("Ingrese el ID del amigo a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    scanner.nextLine();
                    Usuario amigoEliminar = usuarios.get(idEliminar);
                    if (amigoEliminar != null && usuario.getAmigos().contains(amigoEliminar)) {
                        usuario.eliminarAmigo(amigoEliminar);
                        System.out.println("Amigo eliminado.");
                    } else {
                        System.out.println("No tienes un amigo con ese ID.");
                    }
                    break;

                case 4:
                    System.out.print("Ingrese el ID del usuario al que desea enviar un mensaje: ");
                    int idReceptor = scanner.nextInt();
                    scanner.nextLine();
                    Usuario receptor = usuarios.get(idReceptor);

                    if (receptor != null && receptor != usuario) {
                        System.out.print("Escriba su mensaje: ");
                        String contenido = scanner.nextLine();
                        Mensaje mensaje = new Mensaje(usuario, receptor, contenido);
                        receptor.recibirMensaje(mensaje);
                        System.out.println("Mensaje enviado a " + receptor.getNombre());
                    } else {
                        System.out.println("ID inválido o no puedes enviarte mensajes a ti mismo.");
                    }
                    break;

                case 5:
                    usuario.mostrarMensajes();
                    break;

                case 6:
                    System.out.print("Ingrese el ID del usuario con el que desea verificar conexión: ");
                    int idDestino = scanner.nextInt();
                    scanner.nextLine();
                    Usuario destino = usuarios.get(idDestino);

                    if (destino != null && destino != usuario) {
                        boolean conectado = usuario.estaConectadoCon(destino);
                        System.out.println(conectado
                                ? "¡Son amigos!"
                                : "No tiene relación alguna con ese usuario.");
                    } else {
                        System.out.println("ID inválido o no puedes verificar conexión contigo mismo.");
                    }
                    break;

                case 7:
                    System.out.println("Sesión cerrada.");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 4);
    }
}