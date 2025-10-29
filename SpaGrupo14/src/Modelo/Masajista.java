
package Modelo;

public class Masajista {
    private int codMasajista;
    private int matricula;
    private String nombreCompleto;
    private String telefono;
    private boolean estado;

    public Masajista(int codMasajista,int matricula, String nombreCompleto, String telefono, boolean estado) {
        this.codMasajista = codMasajista;
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Masajista(int matricula,String nombreCompleto, String telefono, boolean estado) {
        this.codMasajista = -1;
        this.matricula = matricula;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.estado = estado;
    }

    public Masajista() {
        this.codMasajista = -1;
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Masajista{" + "codMasajista=" + codMasajista + ", matricula=" + matricula +", nombreCompleto=" + nombreCompleto + ", telefono=" + telefono + ", estado=" + estado + '}';
    }
    
    
}
