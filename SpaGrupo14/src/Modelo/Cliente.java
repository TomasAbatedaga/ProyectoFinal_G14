
package Modelo;

public class Cliente {
    private int codCli;
    private int dni;
    private String nombreCompleto;
    private String telefono;
    private int edad;
    private String afecciones;
    private boolean estado;

    public Cliente(int codCli, int dni, String nombreCompleto, String telefono, int edad, String afecciones, boolean estado) {
        this.codCli = codCli;
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.edad = edad;
        this.afecciones = afecciones;
        this.estado = estado;
    }

    public Cliente(int dni, String nombreCompleto, String telefono, int edad, String afecciones, boolean estado) {
        this.codCli = -1;
        this.dni = dni;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.edad = edad;
        this.afecciones = afecciones;
        this.estado = estado;
    }

    public Cliente() {
        this.codCli = -1;
    }

    public int getCodCli() {
        return codCli;
    }

    public void setCodCli(int codCli) {
        this.codCli = codCli;
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
        return "Cliente{" + "codCli=" + codCli + ", dni=" + dni + ", nombreCompleto=" + nombreCompleto + ", telefono=" + telefono + ", edad=" + edad + ", afecciones=" + afecciones + ", estado=" + estado + '}';
    }
    
    
}
