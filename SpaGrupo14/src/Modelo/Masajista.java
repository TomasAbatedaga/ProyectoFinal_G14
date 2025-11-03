
package Modelo;

public class Masajista {
    private int codMasajista;
    private int matricula;
    private String nombreCompleto;
    private String telefono;
    private EspecialidadEnum especialidad;
    private boolean estado;

    public Masajista(int codMasajista, int matricula, String nombreCompleto, String telefono, EspecialidadEnum especialidad, boolean estado) {
        this.codMasajista = codMasajista;
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    public Masajista(int matricula, String nombreCompleto, String telefono, EspecialidadEnum especialidad, boolean estado) {
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    public Masajista() {
    }

    public int getCodMasajista() {
        return codMasajista;
    }

    public void setCodMasajista(int codMasajista) {
        this.codMasajista = codMasajista;
    }

    public int getMatricula() {
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public EspecialidadEnum getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(EspecialidadEnum especialidad) {
        this.especialidad = especialidad;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Masajista{");
        sb.append("codMasajista=").append(codMasajista);
        sb.append(", matricula=").append(matricula);
        sb.append(", nombreCompleto=").append(nombreCompleto);
        sb.append(", telefono=").append(telefono);
        sb.append(", especialidad=").append(especialidad);
        sb.append(", estado=").append(estado);
        sb.append('}');
        return sb.toString();
    }

    
}
