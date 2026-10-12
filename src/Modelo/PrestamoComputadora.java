package Modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class PrestamoComputadora {
    private String idPrestamoComputadora;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private boolean activo;
    private Computadora computadora;
    private Estudiante estudiante;
    //
    public PrestamoComputadora() {
    }

    public String getIdPrestamoComputadora() {
        return idPrestamoComputadora;
    }

    public void setIdPrestamoComputadora(String idPrestamoComputadora) {
        this.idPrestamoComputadora = idPrestamoComputadora;
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

    public Computadora getComputadora() {
        return computadora;
    }

    public void setComputadora(Computadora computadora) {
        this.computadora = computadora;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
}