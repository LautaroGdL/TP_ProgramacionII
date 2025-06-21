package Modelos;

public class PruebaGeneral {
    public static void main(String[] args) {
        System.out.println("========== CASO CORRECTO ==========");
        Usuario alice = new Usuario(1, "Alice");
        Usuario bob = new Usuario(2, "Bob");

        alice.agregarAmigo(bob);
        System.out.println("Amigos de Alice: " + alice.getAmigos());
        System.out.println("Amigos de Bob: " + bob.getAmigos());

        alice.enviarMensaje(bob, "Hola Bob, ¿cómo estás?");
        bob.enviarMensaje(alice, "Bien Alice, gracias. ¿Y vos?");
        alice.verMensajesCon(bob);

        System.out.println("\n========== CASO LÍMITE ==========");
        System.out.println("Intentando agregarse a sí misma...");
        alice.agregarAmigo(alice);  // No debería agregarse a sí misma
        System.out.println("Amigos de Alice tras intento: " + alice.getAmigos());

        Usuario carol = new Usuario(3, "Carol");
        System.out.println("¿Alice está conectada con Carol? " + alice.estaConectadoCon(carol)); // Esperado: false
        alice.agregarAmigo(carol);
        System.out.println("¿Alice está conectada con Carol ahora? " + alice.estaConectadoCon(carol)); // Esperado: true

        System.out.println("\n========== CASO ERROR ==========");
        try {
            System.out.println("Intentando enviar mensaje a null...");
            alice.enviarMensaje(null, "Esto no debería enviarse.");
        } catch (Exception e) {
            System.out.println("Error capturado correctamente: " + e.getClass().getSimpleName() + " - " + e.getMessage());
        }

        System.out.println("\n========== PRUEBAS FINALIZADAS ==========");
    }
}
