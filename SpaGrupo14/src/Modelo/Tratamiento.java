
package Modelo;

import java.util.List;

public class Tratamiento {
    private int codTratam;
    private String nombre;
    private Especialidad tipo;
    private String detalle;
    private List<Producto> productos;
    private int duracion;
    private double costo;
    private boolean estado;

    public Tratamiento(int codTratam, String nombre, Especialidad tipo, String detalle, List<Producto> productos, int duracion, double costo, boolean estado) {
        this.codTratam = codTratam;
        this.nombre = nombre;
        this.tipo = tipo;
        this.detalle = detalle;
        this.productos = productos;
        this.duracion = duracion;
        this.costo = costo;
        this.estado = estado;
    }

    public Tratamiento(String nombre, Especialidad tipo, String detalle, List<Producto> productos, int duracion, double costo, boolean estado) {
        this.codTratam = -1;
        this.nombre = nombre;
        this.tipo = tipo;
        this.detalle = detalle;
        this.productos = productos;
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

    public Especialidad getTipo() {
        return tipo;
    }

    public void setTipo(Especialidad tipo) {
        this.tipo = tipo;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
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
        return "Tratamiento{" + "codTratam=" + codTratam + ", nombre=" + nombre + ", tipo=" + tipo + ", detalle=" + detalle + ", productos=" + productos + ", duracion=" + duracion + ", costo=" + costo + ", estado=" + estado + '}';
    }
    
    
}
