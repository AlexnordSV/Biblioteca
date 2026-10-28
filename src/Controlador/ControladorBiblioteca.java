package Controlador;

import Modelo.Biblioteca;
import Modelo.Bibliotecario;
import Modelo.Estudiante;
import Vista.VistaBiblioteca;

public class ControladorBiblioteca {
    private VistaBiblioteca vistaBiblioteca;
    private Biblioteca biblioteca;

    public ControladorBiblioteca(VistaBiblioteca vistaBiblioteca, Biblioteca biblioteca) {
        this.vistaBiblioteca = vistaBiblioteca;
        this.biblioteca = biblioteca;
    }
    
    public void menuInicioSesion(){
        vistaBiblioteca.mostrarMensaje("===SISTEMA BIBLIOTECARIO-FMP===");
        
        String id = vistaBiblioteca.pedirID();
        String password= vistaBiblioteca.pedirContrasenia();
        
        Estudiante estudiante = biblioteca.validarLoginEstudiante(id, password);
        if(estudiante != null){
            vistaBiblioteca.mostrarMensaje("Inicio de sesión valido!");
            return;
        }
        
        Bibliotecario bibliotecario = biblioteca.validarLoginBibliotecario(id, password);
        if(bibliotecario != null){
            vistaBiblioteca.mostrarMensaje("Inicio de sesión valido!");
            return;
        }
        
        vistaBiblioteca.mostrarMensaje("Credenciales no validas");
    }
    
    
    
    
    public void controladorMostrarBiblioteca(){
        vistaBiblioteca.mostrarDetallesBiblioteca(biblioteca);
    }
}