
package Modelo;

public class Especialidad {
    private int cod_especialidad;
    private String nombre;

    public Especialidad(int cod_especialidad, String nombre) {
        this.cod_especialidad = cod_especialidad;
        this.nombre = nombre;
    }

    public Especialidad(String nombre) {
        this.cod_especialidad  = -1 ;
        this.nombre = nombre;
    }

    public Especialidad() {
        this.cod_especialidad = -1;
    }

    public int getCod_especialidad() {
        return cod_especialidad;
    }

    public void setCod_especialidad(int cod_especialidad) {
        this.cod_especialidad = cod_especialidad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
