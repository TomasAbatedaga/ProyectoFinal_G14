
package Modelo;

public class Instalacion {
    private int codInstal;
    private String nombre;
    private String detalleUso;
    private double precio30M;
    private boolean estado;

    public Instalacion(int codInstal, String nombre, String detalleUso, double precio30M, boolean estado) {
        this.codInstal = codInstal;
        this.nombre = nombre;
        this.detalleUso = detalleUso;
        this.precio30M = precio30M;
        this.estado = estado;
    }

    public Instalacion(String nombre, String detalleUso, double precio30M, boolean estado) {
        this.codInstal = codInstal;
        this.nombre = nombre;
        this.detalleUso = detalleUso;
        this.precio30M = precio30M;
        this.estado = estado;
    }

    public Instalacion() {
        this.codInstal = codInstal;
    }

    public int getCodInstal() {
        return codInstal;
    }

    public void setCodInstal(int codInstal) {
        this.codInstal = codInstal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDetalleUso() {
        return detalleUso;
    }

    public void setDetalleUso(String detalleUso) {
        this.detalleUso = detalleUso;
    }

    public double getPrecio30M() {
        return precio30M;
    }

    public void setPrecio30M(double precio30M) {
        this.precio30M = precio30M;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return  nombre;
    }
    
}
