package CasosDeUso;

import Modelos.Usuario;

//public class PruebaGeneral {
//    public static void main(String[] args) {
//        System.out.println("========== CASO CORRECTO ==========");
//        Usuario alice = new Usuario(1, "Alice");
//        Usuario bob = new Usuario(2, "Bob");
//
//        alice.agregarAmigo(bob);
//        System.out.println("Amigos de Alice: " + alice.getAmigos());
//        System.out.println("Amigos de Bob: " + bob.getAmigos());
//
//        alice.enviarMensaje(bob, "Hola Bob, ¿cómo estás?");
//        bob.enviarMensaje(alice, "Bien Alice, gracias. ¿Y vos?");
//        alice.verMensajesCon(bob);
//
//        System.out.println("\n========== CASO LÍMITE ==========");
//        System.out.println("Intentando agregarse a sí misma...");
//        alice.agregarAmigo(alice);  // No debería agregarse a sí misma
//        System.out.println("Amigos de Alice tras intento: " + alice.getAmigos());
//
//        Usuario carol = new Usuario(3, "Carol");
//        System.out.println("¿Alice está conectada con Carol? " + alice.estaConectadoCon(carol)); // Esperado: false
//        alice.agregarAmigo(carol);
//        System.out.println("¿Alice está conectada con Carol ahora? " + alice.estaConectadoCon(carol)); // Esperado: true
//
//        System.out.println("\n========== CASO ERROR ==========");
//        try {
//            System.out.println("Intentando enviar mensaje a null...");
//            alice.enviarMensaje(null, "Esto no debería enviarse.");
//        } catch (Exception e) {
//            System.out.println("Error capturado correctamente: " + e.getClass().getSimpleName() + " - " + e.getMessage());
//        }
//
//        System.out.println("========== ELIMINACIÓN DE AMIGO Y GRAFO ==========");
//        Usuario ana = new Usuario(1, "Ana", "ana123", "123");
//        Usuario bruno = new Usuario(2, "Bruno", "bruno456", "456");
//        GrafoUsuarios grafo = new GrafoUsuarios();
//
//        grafo.agregarUsuario(ana);
//        grafo.agregarUsuario(bruno);
//
//        ana.agregarAmigo(bruno);
//        grafo.agregarRelacion(ana, bruno);
//
//        System.out.println("Amigos de Ana antes: " + ana.getAmigos());
//        System.out.println("Amigos de Bruno antes: " + bruno.getAmigos());
//
//        ana.eliminarAmigo(bruno, new java.util.ArrayList<>());
//
//        System.out.println("Amigos de Ana después: " + ana.getAmigos());
//        System.out.println("Amigos de Bruno después: " + bruno.getAmigos());
//      
//        System.out.println("\n========== GESTIÓN DE GRUPO ==========");
//        Usuario carla = new Usuario(3, "Carla", "carla789", "789");
//        Usuario daniel = new Usuario(4, "Daniel", "daniel000", "000");
//
//        GrafoGrupo grupo = new GrafoGrupo("Estudio");
//
//        grupo.agregarMiembro(ana);
//        grupo.agregarMiembro(carla);
//        grupo.agregarMiembro(daniel);
//
//        System.out.println("¿Carla es miembro?: " + grupo.esMiembro(carla));
//        System.out.println("¿Daniel es miembro?: " + grupo.esMiembro(daniel));
//
//        grupo.eliminarMiembro(carla);
//        System.out.println("¿Carla sigue en el grupo?: " + grupo.esMiembro(carla));
//
//        System.out.println("\n========== ENVÍO DE MENSAJE VACÍO (CASO LÍMITE) ==========");
//        try {
//            ana.agregarAmigo(bruno); // volver a agregarlos como amigos para poder enviar mensajes
//            ana.enviarMensaje(bruno, "");
//            bruno.verMensajesCon(ana);
//
//            System.out.println("\nIntentando enviar mensaje null...");
//            ana.enviarMensaje(bruno, null); // puede lanzar excepción
//
//        } catch (Exception e) {
//            System.out.println("Error capturado: " + e.getClass().getSimpleName() + " - " + e.getMessage());
//        }
//
//        System.out.println("\n========== PRUEBAS FINALIZADAS ==========");
//    }
//}
