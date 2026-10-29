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
                mostrarMenuBibliotecario(bibliotecario);
                break;
            }

            Estudiante estudiante = biblioteca.validarLoginEstudiante(id.toUpperCase(), password);
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
        int opcion=0;
        do{
            opcion=vistaBiblioteca.menuEstudiante(estudiante);
            switch(opcion){
                case 1:
                    vistaBiblioteca.mostrarMensaje("\nAccediendo al menu de prestamos de libros.");
                    break;
                case 2:
                    vistaBiblioteca.mostrarMensaje("\nAccediendo al menu de prestamos de cabinas.");
                    break;
                case 3:
                    vistaBiblioteca.mostrarMensaje("\nAccediendo al menu de prestamo de computadoras.");
                    break;
                case 4:
                    vistaBiblioteca.mostrarMensaje("\nCerrando sesión!");
                    menuInicioSesion();
                    break;
                    
                default: 
                    vistaBiblioteca.mostrarMensaje("[ERROR], opcion no valida, digite un numero entre el 1 y 4");
            }
            
        }while(opcion!=4);
    }
    
    private void mostrarMenuBibliotecario(Bibliotecario bibliotecario){
        vistaBiblioteca.menuBibliotecario(bibliotecario);
    }
    
    
    public void controladorMostrarBiblioteca(){

    }
}