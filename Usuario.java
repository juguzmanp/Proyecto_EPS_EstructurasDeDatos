public class Usuario {

    private String tipoDocumento;
    private String numeroDocumento;
    private String nombres;
    private String apellidos;
    private int edad;
    private String sexo;

    public Usuario(String tipoDocumento, String numeroDocumento, String nombres,
                   String apellidos, int edad, String sexo) {
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.edad = edad;
        this.sexo = sexo;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    @Override
    public String toString() {
        return nombres + " " + apellidos + " (" + tipoDocumento + " " + numeroDocumento + ")";
    }
}
