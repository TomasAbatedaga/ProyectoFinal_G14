
package Modelo;

public class Instalacion {
    private int cod_Instalacion;
    private String nombre;
    private String detalleUso;
    private double precio;
    private boolean estado;
    private int cont;

    public Instalacion(int cod_Instalacion, String nombre, String detalleUso, double precio, boolean estado) {
        this.cod_Instalacion = cod_Instalacion;
        this.nombre = nombre;
        this.detalleUso = detalleUso;
        this.precio = precio;
        this.estado = estado;
    }

    public Instalacion(String nombre, String detalleUso, double precio, boolean estado) {
        this.cod_Instalacion = cod_Instalacion;
        this.nombre = nombre;
        this.detalleUso = detalleUso;
        this.precio = precio;
        this.estado = estado;
    }

    public Instalacion() {
        this.cod_Instalacion = cod_Instalacion;
    }

    public int getCodInstal() {
        return cod_Instalacion;
    }

    public void setCodInstal(int codInstal) {
        this.cod_Instalacion = codInstal;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

      public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    
    @Override
    public String toString() {
        return  nombre;
    }
    
}
