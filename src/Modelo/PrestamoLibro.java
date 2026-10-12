package Modelo;

import java.time.LocalDate;

public class PrestamoLibro {
    private String idPrestamoLibro;
    private LocalDate fechaInicio;
    private LocalDate fechaDevolucion;
    private String estadoPrestamo;
    private LocalDate devolucionReal;
    private Libro libro;
    private Estudiante estudiante;
    
    public void calcularFechaDevolucion(){}
    public void calcularMora(){}
    public void devolverLibro(){}
    
    //

    public PrestamoLibro() {
    }

    public String getIdPrestamoLibro() {
        return idPrestamoLibro;
    }

    public void setIdPrestamoLibro(String idPrestamoLibro) {
        this.idPrestamoLibro = idPrestamoLibro;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDate fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public String getEstadoPrestamo() {
        return estadoPrestamo;
    }

    public void setEstadoPrestamo(String estadoPrestamo) {
        this.estadoPrestamo = estadoPrestamo;
    }

    public LocalDate getDevolucionReal() {
        return devolucionReal;
    }

    public void setDevolucionReal(LocalDate devolucionReal) {
        this.devolucionReal = devolucionReal;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    
}
