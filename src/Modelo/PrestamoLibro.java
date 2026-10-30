package Modelo;

import java.time.DayOfWeek;
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

    public PrestamoLibro(String idPrestamoLibro, Estudiante estudiante, Libro libro) {
        this.idPrestamoLibro = idPrestamoLibro;
        this.estudiante = estudiante;
        this.libro = libro;
        this.fechaInicio = LocalDate.now();
        this.fechaDevolucion = calcularFechaDevolucion(this.fechaInicio, 3);
        this.devolucionReal = null; // Aún no se devuelve
        this.estadoPrestamo = "Prestado";
    }
    
    

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
    
    public static LocalDate calcularFechaDevolucion(LocalDate fechaInicio, int diasHabiles) {
    LocalDate fechaActual = fechaInicio;
    int diasAgregados = 0;
    
    while (diasAgregados < diasHabiles) {
        fechaActual = fechaActual.plusDays(1);
        // Si el día NO es sábado ni domingo, cuenta como hábil
        if (fechaActual.getDayOfWeek() != DayOfWeek.SATURDAY && 
            fechaActual.getDayOfWeek() != DayOfWeek.SUNDAY) {
            diasAgregados++;
        }
    }
    return fechaActual;
}
    
}
