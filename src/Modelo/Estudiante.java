package Modelo;

import java.util.List;

public class Estudiante {
    private String carnet;
    private String nombreCompleto;
    private String carrera;
    private boolean bloqueo;
    private String contrasenia;
    private boolean prestamoActivo;
    private Biblioteca biblioteca;
    private List<PrestamoLibro> listaPrestamosLibro;
    private List<PrestamoCabina> listaPrestamosCabina;
    private List<PrestamoComputadora> listaPrestamosComputadora;
    
    public void consultarEstado(){}
    public void realizarPrestamo(){}
    public void verPrestamo(){}
    //
    public Estudiante() {
    }

    public Estudiante(String carnet, String nombreCompleto, String carrera, String contrasenia, Biblioteca biblioteca) {
        this.carnet = carnet;
        this.nombreCompleto = nombreCompleto;
        this.carrera = carrera;
        this.contrasenia = contrasenia;
        this.biblioteca = biblioteca;
    }

    
    
    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCarrera() {
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }

    public boolean isBloqueo() {
        return bloqueo;
    }

    public void setBloqueo(boolean bloqueo) {
        this.bloqueo = bloqueo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isPrestamoActivo() {
        return prestamoActivo;
    }

    public void setPrestamoActivo(boolean prestamoActivo) {
        this.prestamoActivo = prestamoActivo;
    }

    public List<PrestamoLibro> getListaPrestamosLibro() {
        return listaPrestamosLibro;
    }

    public void setListaPrestamosLibro(List<PrestamoLibro> listaPrestamosLibro) {
        this.listaPrestamosLibro = listaPrestamosLibro;
    }

    public List<PrestamoCabina> getListaPrestamosCabina() {
        return listaPrestamosCabina;
    }

    public void setListaPrestamosCabina(List<PrestamoCabina> listaPrestamosCabina) {
        this.listaPrestamosCabina = listaPrestamosCabina;
    }

    public List<PrestamoComputadora> getListaPrestamosComputadora() {
        return listaPrestamosComputadora;
    }

    public void setListaPrestamosComputadora(List<PrestamoComputadora> listaPrestamosComputadora) {
        this.listaPrestamosComputadora = listaPrestamosComputadora;
    }   
}