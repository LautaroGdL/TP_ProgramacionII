package Modelos;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GrafoUsuarios grafo = new GrafoUsuarios();
        Map<Integer, Usuario> mapaUsuarios = new HashMap<>();
        List<Usuario> datosDeBaja = new ArrayList<>();
        List<GrafoGrupo> grupos = new ArrayList<>();

        int id = 0;
        int op;

        do {
            // Menu inicial
            System.out.println("\nMenú principal:");
            System.out.println("1. Crear usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Ver usuarios");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                // Se pone los datos del usuario como nombre, username y contraseña. Si ya existe el username te manda al menu para poder volver a crear el otro usuario con otr contraseña
                case 1:
                    System.out.print("Nombre completo: ");
                    String nombre = sc.nextLine();

                    System.out.print("Username: ");
                    String username = sc.nextLine();
                    boolean usernameExiste = mapaUsuarios.values().stream()
                            .anyMatch(u -> u.getUsername().equals(username));
                    if (usernameExiste) {
                        System.out.println("El nombre de usuario ya existe. Elegí otro.");
                        break;
                    }

                    System.out.print("Contraseña: ");
                    String password = sc.nextLine();

                    Usuario nuevo = new Usuario(id, nombre, username, password);
                    mapaUsuarios.put(id, nuevo);
                    grafo.agregarUsuario(nuevo);
                    System.out.println("Usuario creado con ID: " + id);
                    id++;
                    break;

                // Se ingresa el usuario y la contraseña. Si el usuario y la contraseñla estan dentro del mapaUsuario y todo coincide, llama al MenuUsuario
                case 2:
                    System.out.print("Username: ");
                    String user = sc.nextLine();
                    System.out.print("Contraseña: ");
                    String pass = sc.nextLine();
                    Usuario actual = null;

                    for (Usuario u : mapaUsuarios.values()) {
                        if (u.getUsername().equals(user) && u.getPassword().equals(pass)) {
                            actual = u;
                            break;
                        }
                    }

                    if (actual != null) {
                        System.out.println("Sesión iniciada como: " + actual.getNombre() + " (ID: " + actual.getId() + ")");
                        menuUsuario(actual, mapaUsuarios, datosDeBaja, grafo, grupos, sc);
                    } else {
                        System.out.println("Credenciales inválidas.");
                    }
                    break;

                // Trae los valores del mapa de usuarios, con el nombre, username y su ID
                case 3:
                    System.out.println("Usuarios registrados:");
                    for (Usuario u : mapaUsuarios.values()) {
                        System.out.println(u);
                    }
                    break;
            }

        } while (op != 4);

        sc.close();
        System.out.println("Sistema finalizado.");
    }

    // Menu de usuario, este va a recibir el usuario, la lista dr usuarios con su ID, la lista de usuarios dados de baja, el grupo, la lista de grupos y el scanner usado previamente
    private static void menuUsuario(Usuario u, Map<Integer, Usuario> mapa, List<Usuario> bajas,
                                    GrafoUsuarios grafo, List<GrafoGrupo> grupos, Scanner sc) {
        int op;
        do {
            System.out.println("\nMenú Usuario: " + u.getNombre() + " (ID: " + u.getId() + ")");
            System.out.println("1. Ver amigos");
            System.out.println("2. Agregar amigo");
            System.out.println("3. Eliminar amigo");
            System.out.println("4. Enviar mensaje");
            System.out.println("5. Ver mensajes con usuario");
            System.out.println("6. Ver sugerencias de amigos");
            System.out.println("7. Ver si está conectado con otro usuario");
            System.out.println("8. Ver si es amigo de otro usuario");
            System.out.println("9. Ver bandeja");
            System.out.println("10. Crear grupo");
            System.out.println("11. Ver grupos");
            System.out.println("12. Enviar mensaje a grupo");
            System.out.println("13. Ver mensajes de grupo");
            System.out.println("14. Ver grafo de conexiones del grupo");
            System.out.println("15. Agregar usuario a grupo");
            System.out.println("16. Eliminar usuario de grupo");
            System.out.println("17. Salir");
            System.out.print("Seleccione opción: ");
            op = Integer.parseInt(sc.nextLine());

            switch (op) {

                // Muestra el conjunto de amigos del Usuario
                case 1:
                    u.mostrarAmigos();
                    break;

                // Busca por ID a un Usuario y lo agrega al Conjunto de amigos. Tambien forma una relacion de grafos 1:1 entre ambos usuarios
                case 2:
                    System.out.print("ID del amigo a agregar: ");
                    Usuario a = mapa.get(Integer.parseInt(sc.nextLine()));
                    if (a != null) {
                        u.agregarAmigo(a);
                        grafo.agregarRelacion(u, a);
                    }
                    break;

                // Busca por ID a un usuario y lo elimina del conjunto
                case 3:
                    System.out.print("ID del amigo a eliminar: ");
                    Usuario b = mapa.get(Integer.parseInt(sc.nextLine()));
                    if (b != null) {
                        u.eliminarAmigo(b, bajas);
                        System.out.println("Amigo eliminado y datos guardados.");
                    }
                    break;

                // Manda un mensaje a un usuario
                case 4:
                    System.out.print("ID destinatario: ");
                    Usuario dest = mapa.get(Integer.parseInt(sc.nextLine()));
                    if (dest != null) {
                        System.out.print("Mensaje: ");
                        String texto = sc.nextLine();
                        u.enviarMensaje(dest, texto);
                        dest.recibirMensaje(new Mensaje("Mensaje de " + u.getNombre() + ": " + texto));
                    }
                    break;

                // Muestra todos los mensajes intercambiados entre el usuario y otro usuario
                case 5:
                    System.out.print("ID del usuario: ");
                    Usuario otro = mapa.get(Integer.parseInt(sc.nextLine()));
                    if (otro != null) {
                        u.verMensajesCon(otro);
                    }
                    break;

                // Muestra sugerencias de amistad (amigos de amigos) usando el grafo de usuarios
                case 6:
                    System.out.println("Sugerencias de amigos:");
                    Set<Usuario> sugerencias = grafo.sugerenciasDeAmigos(u);
                    for (Usuario s : sugerencias) {
                        System.out.println("- " + s.getNombre() + " (ID: " + s.getId() + ")");
                    }
                    break;

                // Verifica si el usuario actual está conectado con otro (directa o indirectamente)
                case 7:
                    System.out.print("ID del usuario: ");
                    Usuario conectado = mapa.get(Integer.parseInt(sc.nextLine()));
                    if (conectado != null) {
                        System.out.println(u.estaConectadoCon(conectado)
                                ? "Están conectados."
                                : "No están conectados.");
                    }
                    break;

                // Verifica si el usuario actual es amigo directo de otro usuario
                case 8:
                    System.out.print("ID del usuario: ");
                    Usuario amigo = mapa.get(Integer.parseInt(sc.nextLine()));
                    System.out.println(u.esAmigoDe(amigo) ? "Sí son amigos." : "No son amigos.");
                    break;

                // Muestra toda la bandeja de mensajes del usuario actual
                case 9:
                    u.mostrarMensajes();
                    break;

                // Crea un nuevo grupo y agrega al usuario actual como miembro
                case 10:
                    System.out.print("Nombre del grupo: ");
                    String nombreGrupo = sc.nextLine();
                    GrafoGrupo nuevoGrupo = new GrafoGrupo(nombreGrupo);
                    nuevoGrupo.agregarMiembro(u);
                    grupos.add(nuevoGrupo);
                    System.out.println("Grupo creado y se te ha agregado.");
                    break;

                // Muestra todos los grupos en los que el usuario es miembro
                case 11:
                    System.out.println("Grupos en los que participás:");
                    for (GrafoGrupo g : grupos) {
                        if (g.esMiembro(u)) {
                            System.out.println("- " + g.getNombre());
                        }
                    }
                    break;

                // Envía un mensaje a un usuario dentro de un grupo determinado
                case 12:
                    System.out.print("Nombre del grupo: ");
                    String grupoNombre = sc.nextLine();
                    GrafoGrupo gEnviar = grupos.stream()
                            .filter(g -> g.getNombre().equalsIgnoreCase(grupoNombre))
                            .findFirst().orElse(null);

                    if (gEnviar != null && gEnviar.esMiembro(u)) {
                        System.out.print("ID del destinatario dentro del grupo: ");
                        int idDest = Integer.parseInt(sc.nextLine());
                        Usuario destino = mapa.get(idDest);
                        if (destino != null && gEnviar.esMiembro(destino)) {
                            System.out.print("Mensaje: ");
                            String contenido = sc.nextLine();
                            gEnviar.enviarMensaje(u, destino, contenido);
                        } else {
                            System.out.println("El destinatario no pertenece al grupo.");
                        }
                    } else {
                        System.out.println("No sos miembro del grupo o no existe.");
                    }
                    break;

                // Muestra todos los mensajes del grupo si el usuario es miembro
                case 13:
                    System.out.print("Nombre del grupo: ");
                    String grupoVer = sc.nextLine();
                    GrafoGrupo gVer = grupos.stream()
                            .filter(g -> g.getNombre().equalsIgnoreCase(grupoVer))
                            .findFirst().orElse(null);
                    if (gVer != null && gVer.esMiembro(u)) {
                        gVer.mostrarMensajes();
                    } else {
                        System.out.println("No se encontró el grupo o no sos miembro.");
                    }
                    break;

                // Muestra el grafo de conexiones entre miembros del grupo
                case 14:
                    System.out.print("Nombre del grupo: ");
                    String grupoMostrar = sc.nextLine();
                    GrafoGrupo gConexiones = grupos.stream()
                            .filter(g -> g.getNombre().equalsIgnoreCase(grupoMostrar))
                            .findFirst().orElse(null);

                    if (gConexiones != null && gConexiones.esMiembro(u)) {
                        gConexiones.mostrarGrafo();
                    } else {
                        System.out.println("No estás en ese grupo o no existe.");
                    }
                    break;

                // Agrega otro usuario al grupo si el usuario actual ya es miembro
                case 15:
                    System.out.print("Nombre del grupo: ");
                    String grupoAgregar = sc.nextLine();
                    GrafoGrupo gAgregar = grupos.stream()
                            .filter(g -> g.getNombre().equalsIgnoreCase(grupoAgregar))
                            .findFirst().orElse(null);

                    if (gAgregar != null && gAgregar.esMiembro(u)) {
                        System.out.print("ID del usuario que querés agregar: ");
                        int idNuevo = Integer.parseInt(sc.nextLine());
                        Usuario nuevoMiembro = mapa.get(idNuevo);

                        if (nuevoMiembro != null) {
                            gAgregar.agregarMiembro(nuevoMiembro);
                            System.out.println(nuevoMiembro.getNombre() + " fue agregado al grupo.");
                        } else {
                            System.out.println("Usuario no encontrado.");
                        }
                    } else {
                        System.out.println("El grupo no existe o no sos miembro.");
                    }
                    break;

                // Elimina a un usuario del grupo si el actual es miembro
                case 16:
                    System.out.print("Nombre del grupo: ");
                    String grupoEliminar = sc.nextLine();
                    GrafoGrupo gEliminar = grupos.stream()
                            .filter(g -> g.getNombre().equalsIgnoreCase(grupoEliminar))
                            .findFirst().orElse(null);

                    if (gEliminar != null && gEliminar.esMiembro(u)) {
                        System.out.print("ID del usuario a eliminar: ");
                        int idElim = Integer.parseInt(sc.nextLine());
                        Usuario elim = mapa.get(idElim);

                        if (elim != null && gEliminar.esMiembro(elim)) {
                            gEliminar.eliminarMiembro(elim);
                        } else {
                            System.out.println("El usuario no está en el grupo.");
                        }
                    } else {
                        System.out.println("No tenés permiso o el grupo no existe.");
                    }
                    break;

            }

        // 17: salir del menú
        } while (op != 17); 
    }
}