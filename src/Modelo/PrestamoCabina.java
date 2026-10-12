package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class PrestamoCabina {
    private String idPrestamoCabina;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean activo;
    private Cabina cabina;
    private Estudiante estudiante;
    
    //

    public PrestamoCabina() {
    }

    public String getIdPrestamoCabina() {
        return idPrestamoCabina;
    }

    public void setIdPrestamoCabina(String idPrestamoCabina) {
        this.idPrestamoCabina = idPrestamoCabina;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public Cabina getCabina() {
        return cabina;
    }

    public void setCabina(Cabina cabina) {
        this.cabina = cabina;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}