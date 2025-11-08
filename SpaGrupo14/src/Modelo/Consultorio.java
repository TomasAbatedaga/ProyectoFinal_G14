
package Modelo;

public class Consultorio {
    private int cod_Consultorio;
    private int nroConsultorio;
    private String usos;
    private String equipamiento;
    private boolean estado;

    public Consultorio(int codConsultorio, int nroConsultorio, String usos, String equipamiento, boolean estado) {
        this.cod_Consultorio = cod_Consultorio;
        this.nroConsultorio = nroConsultorio;
        this.usos = usos;
        this.equipamiento = equipamiento;
        this.estado = estado;
    }

    public Consultorio(int nroConsultorio, String usos, String equipamiento, boolean estado) {
        this.cod_Consultorio = -1;
        this.nroConsultorio = nroConsultorio;
        this.usos = usos;
        this.equipamiento = equipamiento;
        this.estado = estado;
    }

    public Consultorio() {
        this.cod_Consultorio = -1;
    }

    public int getCodConsultorio() {
        return cod_Consultorio;
    }

    public void setCodConsultorio(int cod_Consultorio) {
        this.cod_Consultorio = cod_Consultorio;
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
        return "Consultorio{" + "codConsultorio=" + cod_Consultorio + ", nroConsultorio=" + nroConsultorio + ", usos=" + usos + ", equipamiento=" + equipamiento + ", estado=" + estado + '}';
    }
    
    
    
}
