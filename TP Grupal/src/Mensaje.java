public class Mensaje {
    private final Usuario emisor;
    private final Usuario receptor;
    private final String contenido;

    public Mensaje(Usuario emisor, Usuario receptor, String contenido) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.contenido = contenido;
    }

    public Usuario getEmisor() {
        return emisor;
    }

    public Usuario getReceptor() {
        return receptor;
    }

    public String getContenido() {
        return contenido;
    }

    @Override
    public String toString() {
        return "De: " + emisor.getNombre() + " → " + contenido;
    }
}
