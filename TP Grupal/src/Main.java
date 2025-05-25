import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        HashMap<Integer, Usuario> usuarios = new HashMap<>();
        Scanner scanner = new Scanner(System.in);
        int opcion;
        int contadorId = 0;

        do {
            System.out.println("\n----- Menú de Usuarios -----");
            System.out.println("1. Crear usuario");
            System.out.println("2. Eliminar usuario");
            System.out.println("3. Ver usuarios");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre del nuevo usuario: ");
                    String nombre = scanner.nextLine();
                    Usuario nuevoUsuario = new Usuario(contadorId, nombre);
                    usuarios.put(contadorId, nuevoUsuario);
                    System.out.println("Usuario creado con ID: " + contadorId);
                    contadorId++;
                    break;

                case 2:
                    System.out.print("Ingrese el ID del usuario a eliminar: ");
                    int idEliminar = scanner.nextInt();
                    if (usuarios.containsKey(idEliminar)) {
                        usuarios.remove(idEliminar);
                        System.out.println("Usuario con ID " + idEliminar + " eliminado.");
                    } else {
                        System.out.println("No existe un usuario con ese ID.");
                    }
                    break;

                case 3:
                    System.out.println("Lista de usuarios:");
                    if (usuarios.isEmpty()) {
                        System.out.println("No hay usuarios registrados.");
                    } else {
                        for (Usuario usuario : usuarios.values()) {
                            System.out.println(usuario);
                        }
                    }
                    break;

                case 4:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }

        } while (opcion != 4);

        scanner.close();

    }
}