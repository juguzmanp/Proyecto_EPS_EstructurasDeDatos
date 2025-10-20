public class UsuarioUrgencias {

    private Usuario usuario;
    private int nivelTriage;

    public UsuarioUrgencias (Usuario usuario, int nivelTriage){

        this.usuario = usuario;
        this.nivelTriage = nivelTriage;
    }

    public int getNivelTriage() {
        return nivelTriage;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    @Override
    public String toString() {
        return usuario.toString() + " - Prioridad " + nivelTriage;
    }
}
