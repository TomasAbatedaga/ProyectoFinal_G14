package Modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Dia_de_Spa {

    private int codPack;
    private LocalDateTime fechaYHora;
    private String preferencias;
    private Cliente cliente;
    private List<Sesion> sesiones;
    private double monto;
    private boolean estado;

    public Dia_de_Spa(int codPack, LocalDateTime fechaYHora, String preferencias, Cliente cliente, List<Sesion> sesiones, double monto, boolean estado) {
        this.codPack = codPack;
        this.fechaYHora = fechaYHora;
        this.preferencias = preferencias;
        this.cliente = cliente;
        this.sesiones = sesiones;
        this.monto = monto;
        this.estado = estado;
    }

    public Dia_de_Spa(LocalDateTime fechaYHora, String preferencias, Cliente cliente, List<Sesion> sesiones, double monto, boolean estado) {
        this.codPack = -1;
        this.fechaYHora = fechaYHora;
        this.preferencias = preferencias;
        this.cliente = cliente;
        this.sesiones = sesiones;
        this.monto = monto;
        this.estado = estado;
    }

    public Dia_de_Spa() {
        this.codPack = -1;
    }

    public int getCodPack() {
        return codPack;
    }

    public void setCodPack(int codPack) {
        this.codPack = codPack;
    }

    public LocalDateTime getFechaYHora() {
        return fechaYHora;
    }

    public void setFechaYHora(LocalDateTime fechaYHora) {
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

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Dia_de_Spa{" + "codPack=" + codPack + ", fechaHoraInicio=" + fechaYHora + ", preferencias=" + preferencias + ", cliente=" + cliente + ", sesiones=" + sesiones + ", monto=" + monto + ", estado=" + estado + '}';
    }

    
}
