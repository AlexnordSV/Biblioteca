package Main;

import Modelo.Biblioteca;
import Controlador.ControladorBiblioteca;
import Vista.VistaBiblioteca;

public class Main {
    public static void main(String[] args){
        Biblioteca bibliotecaFmp=new Biblioteca();
        bibliotecaFmp.setSede("Biblioteca UES-FMP");
        bibliotecaFmp.setTelefono("6700-7090"); 
        bibliotecaFmp.setCorreo("bibliotecaFMP@hotmail.com");
        
        VistaBiblioteca vistaBiblioteca = new VistaBiblioteca(bibliotecaFmp);
        ControladorBiblioteca controladorBiblioteca = new ControladorBiblioteca(vistaBiblioteca, bibliotecaFmp);
        controladorBiblioteca.controladorMostrarBiblioteca();
    }   
}