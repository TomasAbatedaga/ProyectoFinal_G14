
package Modelo;

import java.util.List;

public class Tratamiento {
    private int codTratam;
    private String nombre;
    private EspecialidadEnum especialidad;
    private String detalle;
    private int duracion;
    private double costo;
    private boolean estado;
    private int cantidadSesiones;

    public Tratamiento(int codTratam, String nombre, EspecialidadEnum especialidad, String detalle, int duracion, double costo, boolean estado) {
        this.codTratam = codTratam;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.detalle = detalle;
        this.duracion = duracion;
        this.costo = costo;
        this.estado = estado;
    }

    public Tratamiento( String nombre,EspecialidadEnum especialidad, String detalle, int duracion, double costo, boolean estado) {
        this.codTratam = codTratam;
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.detalle = detalle;
        this.duracion = duracion;
        this.costo = costo;
        this.estado = estado;
    }

    public Tratamiento() {
        this.codTratam = codTratam ;
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

    public EspecialidadEnum getEspecialidad() {
        return especialidad;
    }
  
    public void setEspecialidad(EspecialidadEnum especialidad) {
        this.especialidad = especialidad;
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
    
     public int getCantidadSesiones() {
        return cantidadSesiones;
    }

    public void setCantidadSesiones(int cantidadSesiones) {
        this.cantidadSesiones = cantidadSesiones;
    }

    @Override
    public String toString() {
        return "Nombre=" + nombre ;
    }
    
    
}
