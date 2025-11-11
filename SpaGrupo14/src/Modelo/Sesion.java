
package Modelo;

import java.time.LocalDateTime;
import java.util.List;
import java.sql.Time;

public class Sesion {
    private int codSesion;
    private Time fechaHoraInicio;
    private Time fechaHoraFin;
    private Tratamiento tratamiento;
    private Consultorio consultorio;
    private Masajista Masajista;
    private Instalacion instalaciones;
    private Dia_de_Spa diaDeSpa;
    private boolean estado;

    public Sesion() {
    }

    public Sesion(int codSesion, Time fechaHoraInicio, Time fechaHoraFin, Tratamiento tratamiento, Consultorio consultorio, Masajista Masajista, Instalacion instalaciones, Dia_de_Spa diaDeSpa, boolean estado) {
        this.codSesion = codSesion;
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.tratamiento = tratamiento;
        this.consultorio = consultorio;
        this.Masajista = Masajista;
        this.instalaciones = instalaciones;
        this.diaDeSpa = diaDeSpa;
        this.estado = estado;
    }

    public Sesion(Time fechaHoraInicio, Time fechaHoraFin, Tratamiento tratamiento, Consultorio consultorio, Masajista Masajista, Instalacion instalaciones, Dia_de_Spa diaDeSpa, boolean estado) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.fechaHoraFin = fechaHoraFin;
        this.tratamiento = tratamiento;
        this.consultorio = consultorio;
        this.Masajista = Masajista;
        this.instalaciones = instalaciones;
        this.diaDeSpa = diaDeSpa;
        this.estado = estado;
    }

    public int getCodSesion() {
        return codSesion;
    }

    public void setCodSesion(int codSesion) {
        this.codSesion = codSesion;
    }

    public Time getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Time fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Time getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Time fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Tratamiento getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(Tratamiento tratamiento) {
        this.tratamiento = tratamiento;
    }

    public Consultorio getConsultorio() {
        return consultorio;
    }

    public void setConsultorio(Consultorio consultorio) {
        this.consultorio = consultorio;
    }

    public Masajista getMasajista() {
        return Masajista;
    }

    public void setMasajista(Masajista Masajista) {
        this.Masajista = Masajista;
    }

    public Instalacion getInstalaciones() {
        return instalaciones;
    }

    public void setInstalaciones(Instalacion instalaciones) {
        this.instalaciones = instalaciones;
    }

    public Dia_de_Spa getDiaDeSpa() {
        return diaDeSpa;
    }

    public void setDiaDeSpa(Dia_de_Spa diaDeSpa) {
        this.diaDeSpa = diaDeSpa;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Sesion{" + "codSesion=" + codSesion + ", fechaHoraInicio=" + fechaHoraInicio + ", fechaHoraFin=" + fechaHoraFin + ", tratamiento=" + tratamiento + ", consultorio=" + consultorio + ", Masajista=" + Masajista + ", instalaciones=" + instalaciones + ", diaDeSpa=" + diaDeSpa + ", estado=" + estado + '}';
    }

    
}
