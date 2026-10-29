package Main;

import Modelo.Biblioteca;
import Controlador.ControladorBiblioteca;
import Modelo.Bibliotecario;
import Modelo.Estudiante;
import Vista.VistaBiblioteca;

public class Main {
    public static void main(String[] args){
        Biblioteca bibliotecaFmp=new Biblioteca();
        bibliotecaFmp.setSede("Biblioteca UES-FMP");
        bibliotecaFmp.setTelefono("6700-7090"); 
        bibliotecaFmp.setCorreo("bibliotecaFMP@hotmail.com");
        
        //String dui, String idTrabajador, String nombreCompleto, String correo, String usuario, String contrasenia, Biblioteca biblioteca
        
        Bibliotecario bibliotecario1=new Bibliotecario("074926-1", "T001", "Juan Carlos Aboroque", "juancito@gmail.com", "JuanCar", "1234", bibliotecaFmp);
        //String carnet, String nombreCompleto, String carrera, String contrasenia, Biblioteca biblioteca
        Estudiante estudiante1=new Estudiante("HA26002", "Diego Alexander Hernandez Aguillon", "Ingeniería en Sistemas", "1234", bibliotecaFmp);

        bibliotecaFmp.agregarEstudiante(estudiante1);
        bibliotecaFmp.registrarBibliotecario(bibliotecario1);
        
        VistaBiblioteca vistaBiblioteca = new VistaBiblioteca(bibliotecaFmp);
        ControladorBiblioteca controladorBiblioteca = new ControladorBiblioteca(vistaBiblioteca, bibliotecaFmp);
        controladorBiblioteca.menuInicioSesion();
    }   
}