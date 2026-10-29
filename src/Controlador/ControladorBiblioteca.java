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
        boolean acceso=false;
        
        while (!acceso){
            vistaBiblioteca.mostrarMensaje("===SISTEMA BIBLIOTECARIO-FMP===");

            String id = vistaBiblioteca.pedirID();
            String password = vistaBiblioteca.pedirContrasenia();
            
            Bibliotecario bibliotecario = biblioteca.validarLoginBibliotecario(id, password);
            if (bibliotecario != null) {
                vistaBiblioteca.mostrarMensaje("Inicio de sesión valido!");
                acceso=true;
                
                break;
            }

            Estudiante estudiante = biblioteca.validarLoginEstudiante(id, password);
            if (estudiante != null) {
                vistaBiblioteca.mostrarMensaje("Inicio de sesión valido!");
                acceso=true;
                mostrarMenuEstudiante(estudiante);
                break;
                
            }

            vistaBiblioteca.mostrarMensaje("Credenciales no validas");
        }

    }
    
    private void mostrarMenuEstudiante(Estudiante estudiante){
        vistaBiblioteca.menuEstudiante(estudiante);
    }
    
    private void mostrarMenuBibliotecario(){
        
    }
    
    
    public void controladorMostrarBiblioteca(){

    }
}