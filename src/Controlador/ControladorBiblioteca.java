package Controlador;

import Modelo.Biblioteca;
import Modelo.Bibliotecario;
import Modelo.Estudiante;
import Modelo.Libro;
import Modelo.PrestamoLibro;
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
                    mostrarMenuLibros(estudiante);
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
    
    private void mostrarMenuLibros(Estudiante estudiante){
        int opcion=0;
       
        do{
            opcion=vistaBiblioteca.menuPrestamoLibro();
            switch(opcion){
            
                case 1:
                    vistaBiblioteca.mostrarMensaje("Abriendo menu de prestamo de libros");
                    prestamoLibro(estudiante);
                    break;
                case 2:
                    vistaBiblioteca.mostrarMensaje("Desplegando estanterias... ");
                    break;
                case 3:
                    vistaBiblioteca.mostrarMensaje("Consultando en el sistema si existe un prestamo activo...");
                    break;
                case 4:
                    vistaBiblioteca.mostrarMensaje("Ejecutando la devolucion de libro...");
                    break;
                case 5: 
                    vistaBiblioteca.mostrarMensaje("Volviendo al menu anterior...");
                    mostrarMenuEstudiante(estudiante);
                    break;
                default:
                    System.out.println("Opción no valida, ingrese una opción entre 1 y 5");
                    break;
                        
            }
        }while(opcion!=5);
    }
    
    private void prestamoLibro(Estudiante estudiante){
        String codigo = vistaBiblioteca.realizarPrestamoLibro();
        
        Libro libro = biblioteca.buscarLibro(codigo);
        
        if(libro == null){
            vistaBiblioteca.mostrarMensaje("[Error] no se encontró ningun libro registrado con: "+codigo);
            return;
        }
        
        vistaBiblioteca.mostrarMensaje("Libro encontrado, registrando: "+libro.getTitulo());
        String idPrestamo = "P-" + System.currentTimeMillis();
        PrestamoLibro newPrestamo = new PrestamoLibro();
        
        libro.setDisponible(false);
        vistaBiblioteca.mostrarComprobantePrestamo(newPrestamo);
        
        
    }
    
    private void mostrarMenuBibliotecario(Bibliotecario bibliotecario){
        vistaBiblioteca.menuBibliotecario(bibliotecario);
    }
    
    
    public void controladorMostrarBiblioteca(){

    }
}