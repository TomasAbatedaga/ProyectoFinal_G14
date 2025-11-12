
package Modelo;

public class Cliente {
    private int cod_cliente;
    private int dni;
    private String nombreCompleto;
    private String telefono;
    private int edad;
    private String afecciones;
    private boolean estado;

    public Cliente(int cod_cliente, int dni, String nombreCompleto, String telefono, int edad, String afecciones, boolean estado) {
        this.cod_cliente = cod_cliente;
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.edad = edad;
        this.afecciones = afecciones;
        this.estado = estado;
    }

    public Cliente(int dni, String nombreCompleto, String telefono, int edad, String afecciones, boolean estado) {
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.edad = edad;
        this.afecciones = afecciones;
        this.estado = estado;
    }

    public Cliente() {
    }

    public int getCod_cliente() {
        return cod_cliente;
    }

    public void setCod_cliente(int cod_cliente) {
        this.cod_cliente = cod_cliente;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getAfecciones() {
        return afecciones;
    }

    public void setAfecciones(String afecciones) {
        this.afecciones = afecciones;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return nombreCompleto;
    }
}
