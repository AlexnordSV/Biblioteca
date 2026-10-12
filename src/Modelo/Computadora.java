package Modelo;

import java.util.List;

public class Computadora {
    private String idComputadora;
    private String equipo;
    private String sistemaOperativo;
    private String usuarioSistema;
    private String estado;
    private Biblioteca biblioteca;
    private List<PrestamoComputadora> listaPrestamosComputadoras;
    
    //
    public Computadora() {
    }

    public String getIdComputadora() {
        return idComputadora;
    }

    public void setIdComputadora(String idComputadora) {
        this.idComputadora = idComputadora;
    }

    public String getEquipo() {
        return equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }

    public String getSistemaOperativo() {
        return sistemaOperativo;
    }

    public void setSistemaOperativo(String sistemaOperativo) {
        this.sistemaOperativo = sistemaOperativo;
    }

    public String getUsuarioSistema() {
        return usuarioSistema;
    }

    public void setUsuarioSistema(String usuarioSistema) {
        this.usuarioSistema = usuarioSistema;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }

    public List<PrestamoComputadora> getListaPrestamosComputadoras() {
        return listaPrestamosComputadoras;
    }

    public void setListaPrestamosComputadoras(List<PrestamoComputadora> listaPrestamosComputadoras) {
        this.listaPrestamosComputadoras = listaPrestamosComputadoras;
    }
}