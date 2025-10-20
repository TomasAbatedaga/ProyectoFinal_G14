
package Modelo;

public class Producto {
    private int codProducto;
    private String nombre;
    private String descripcion;
    private double precio;
    private Especialidad especialidad;
    private boolean estado;

    public Producto(int codProducto, String nombre, String descripcion, double precio, Especialidad especialidad, boolean estado) {
        this.codProducto = codProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    public Producto(String nombre, String descripcion, double precio, Especialidad especialidad, boolean estado) {
        this.codProducto = -1;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    public Producto() {
        this.codProducto = -1;
    }

    public int getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(int codProducto) {
        this.codProducto = codProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
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
        return "Producto{" + "codProducto=" + codProducto + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + ", especialidad=" + especialidad + ", estado=" + estado + '}';
    }
    
    
}
