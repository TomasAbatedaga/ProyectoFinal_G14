
package Modelo;

import java.util.List;

public class Tratamiento {
    private int codTratam;
    private String nombre;
    private String detalle;
    private int duracion;
    private double costo;
    private boolean estado;

    public Tratamiento(int codTratam, String nombre, String detalle, int duracion, double costo, boolean estado) {
        this.codTratam = codTratam;
        this.nombre = nombre;
        this.detalle = detalle;
        this.duracion = duracion;
        this.costo = costo;
        this.estado = estado;
    }

    public Tratamiento(String nombre, String detalle, int duracion, double costo, boolean estado) {
        this.codTratam = -1;
        this.nombre = nombre;
        this.detalle = detalle;
        this.duracion = duracion;
        this.costo = costo;
        this.estado = estado;
    }

    public Tratamiento() {
        this.codTratam = -1 ;
    }

    public int getCodTratam() {
        return codTratam;
    }

    public void setCodTratam(int codTratam) {
        this.codTratam = codTratam;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Tratamiento{" + "codTratam=" + codTratam + ", nombre=" + nombre + ", detalle=" + detalle + ", duracion=" + duracion + ", costo=" + costo + ", estado=" + estado + '}';
    }
    
    
}
