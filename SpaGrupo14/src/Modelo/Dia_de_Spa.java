package Modelo;

import java.time.LocalDate;
import java.util.List;

public class Dia_de_Spa {

    private int codPack;
    private LocalDate fechaYHora;
    private String preferencias;
    private Cliente cliente;
    private List<Sesion> sesiones;
    private double monto;
    private boolean estado;

    public Dia_de_Spa(int codPack, LocalDate fechaYHora, String preferencias, Cliente cliente, List<Sesion> sesiones, double monto, boolean estado) {
        this.codPack = codPack;
        this.fechaYHora = fechaYHora;
        this.preferencias = preferencias;
        this.cliente = cliente;
        this.sesiones = sesiones;
        this.monto = monto;
        this.estado = estado;
    }

    public Dia_de_Spa(LocalDate fechaYHora, String preferencias, Cliente cliente, double monto, boolean estado) {
        this.fechaYHora = fechaYHora;
        this.preferencias = preferencias;
        this.cliente = cliente;
        this.monto = monto;
        this.estado = estado;
    }
    

    public Dia_de_Spa(LocalDate fechaYHora, String preferencias, Cliente cliente, List<Sesion> sesiones, double monto, boolean estado) {
        this.codPack = codPack;
        this.fechaYHora = fechaYHora;
        this.preferencias = preferencias;
        this.cliente = cliente;
        this.sesiones = sesiones;
        this.monto = monto;
        this.estado = estado;
    }

    public Dia_de_Spa() {
        this.codPack = codPack;
    }

    public int getCodPack() {
        return codPack;
    }

    public void setCodPack(int codPack) {
        this.codPack = codPack;
    }

    public LocalDate getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDate fechaYHora) {
        this.fechaYHora = fechaYHora;
    }

    public String getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(String preferencias) {
        this.preferencias = preferencias;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Sesion> getSesiones() {
        return sesiones;
    }

    public void setSesiones(List<Sesion> sesiones) {
        this.sesiones = sesiones;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return codPack + "";
    }

    
}
