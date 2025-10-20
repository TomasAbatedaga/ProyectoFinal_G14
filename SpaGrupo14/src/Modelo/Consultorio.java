
package Modelo;

public class Consultorio {
    private int codConsultorio;
    private int nroConsultorio;
    private String usos;
    private String equipamiento;
    private boolean estado;

    public Consultorio(int codConsultorio, int nroConsultorio, String usos, String equipamiento, boolean estado) {
        this.codConsultorio = codConsultorio;
        this.nroConsultorio = nroConsultorio;
        this.usos = usos;
        this.equipamiento = equipamiento;
        this.estado = estado;
    }

    public Consultorio(int nroConsultorio, String usos, String equipamiento, boolean estado) {
        this.codConsultorio = -1;
        this.nroConsultorio = nroConsultorio;
        this.usos = usos;
        this.equipamiento = equipamiento;
        this.estado = estado;
    }

    public Consultorio() {
        this.codConsultorio = -1;
    }

    public int getCodConsultorio() {
        return codConsultorio;
    }

    public void setCodConsultorio(int codConsultorio) {
        this.codConsultorio = codConsultorio;
    }

    public int getNroConsultorio() {
        return nroConsultorio;
    }

    public void setNroConsultorio(int nroConsultorio) {
        this.nroConsultorio = nroConsultorio;
    }

    public String getUsos() {
        return usos;
    }

    public void setUsos(String usos) {
        this.usos = usos;
    }

    public String getEquipamiento() {
        return equipamiento;
    }

    public void setEquipamiento(String equipamiento) {
        this.equipamiento = equipamiento;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Consultorio{" + "codConsultorio=" + codConsultorio + ", nroConsultorio=" + nroConsultorio + ", usos=" + usos + ", equipamiento=" + equipamiento + ", estado=" + estado + '}';
    }
    
    
    
}
