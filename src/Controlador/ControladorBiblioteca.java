package Controlador;

import Modelo.Biblioteca;
import Vista.VistaBiblioteca;

public class ControladorBiblioteca {
    private VistaBiblioteca vistaBiblioteca;
    private Biblioteca biblioteca;

    public ControladorBiblioteca(VistaBiblioteca vistaBiblioteca, Biblioteca biblioteca) {
        this.vistaBiblioteca = vistaBiblioteca;
        this.biblioteca = biblioteca;
    }
    
    public void controladorMostrarBiblioteca(){
        vistaBiblioteca.mostrarDetallesBiblioteca(biblioteca);
    }
}