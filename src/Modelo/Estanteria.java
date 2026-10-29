package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Estanteria {
    private String idEstanteria;
    private String catalogo;
    private int capacidad;
    private int capacidadActual;
    private Biblioteca biblioteca;
    private List<Libro> listaLibros = new ArrayList<Libro>();

    public Estanteria(String idEstanteria, String catalogo, int capacidad, Biblioteca biblioteca) {
        this.idEstanteria = idEstanteria;
        this.catalogo = catalogo;
        this.capacidad = capacidad;
        this.biblioteca = biblioteca;
    }
    
    public void añadirLibro(Libro libro){
        this.listaLibros.add(libro);
    }
    public void retirarLibro(){}
    public boolean estaLlena(){return false;}
    public void agregarLibro(){}
    public void removerLibro(){}
    
    //
    public Estanteria() {
    }

    public String getIdEstanteria() {
        return idEstanteria;
    }

    public void setIdEstanteria(String idEstanteria) {
        this.idEstanteria = idEstanteria;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(int capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }
}