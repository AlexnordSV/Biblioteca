package Modelo;

import java.util.List;

public class Cabina {
    private String idCabina;
    private String nombre;
    private Biblioteca biblioteca;
    private int capacidad;
    private boolean disponible;
    private List<ReservaCabina> listaPrestamosCabina;
    
    //

    public Cabina(String idCabina, String nombre, int capacidadV) {
        this.idCabina = idCabina;
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.disponible = true;
    }

    public String getIdCabina() {
        return idCabina;
    }

    public void setIdCabina(String idCabina) {
        this.idCabina = idCabina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public int getCapcaidad() {
        return capacidad;
    }

    public void setCapcaidad(int capcaidad) {
        this.capacidad = capcaidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public List<ReservaCabina> getListaPrestamosCabina() {
        return listaPrestamosCabina;
    }

    public void setListaPrestamosCabina(List<ReservaCabina> listaPrestamosCabina) {
        this.listaPrestamosCabina = listaPrestamosCabina;
    }
    
}