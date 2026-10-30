package Main;

import Modelo.Biblioteca;
import Controlador.ControladorBiblioteca;
import Modelo.Bibliotecario;
import Modelo.Estanteria;
import Modelo.Estudiante;
import Modelo.Libro;
import Vista.VistaBiblioteca;

public class Main {
    public static void main(String[] args){
        Biblioteca bibliotecaFmp=new Biblioteca();
        bibliotecaFmp.setSede("Biblioteca UES-FMP");
        bibliotecaFmp.setTelefono("6700-7090"); 
        bibliotecaFmp.setCorreo("bibliotecaFMP@hotmail.com");
        
        //String idEstanteria, String catalogo, int capacidad, Biblioteca biblioteca
        Estanteria estanteria1=new Estanteria("E001", "Clasicos", 100, bibliotecaFmp);
        Estanteria estanteria2=new Estanteria("E001", "Matematicas, Ciencias Exactas y Fisica", 150, bibliotecaFmp);
        
        
        //String isbn, String titulo, String autor, int numPaginas, String codigo
        Libro libro1=new Libro("ISBN-0345-0945", "El Retrato de Dorian Gray", "Oscar Wilde", 100, "UESLIB-001");
        Libro libro2=new Libro("ISBN-0342-1234", "El Gran Gatsby", "Scott Fitzgerald", 120, "UESLIB-002");
        
        
        //String dui, String idTrabajador, String nombreCompleto, String correo, String usuario, String contrasenia, Biblioteca biblioteca
        
        Bibliotecario bibliotecario1=new Bibliotecario("074926-1", "T001", "Juan Carlos Aboroque", "juancito@gmail.com", "JuanCar", "1234", bibliotecaFmp);
        Bibliotecario bibliotecario2=new Bibliotecario("034595-5", "T002", "Aleyda Adilene Flores Arevalo", "Aleydita@gmail.com", "AdiLu23", "csi202", bibliotecaFmp);
        //String carnet, String nombreCompleto, String carrera, String contrasenia, Biblioteca biblioteca
        Estudiante estudiante1=new Estudiante("HA26002", "Diego Alexander Hernandez Aguillon", "Ingeniería en Sistemas", "La UES.", bibliotecaFmp);
        Estudiante estudiante2=new Estudiante("II26022", "Juan Carlos Ibarrete Ibarrete", "Licenciatura en idiomas", "Carlitos1234", bibliotecaFmp);
        Estudiante estudiante3=new Estudiante("CT26032", "Juan Gerson Cruz Torre", "Licenciatura en Adminstracion de Empresas", "JuanCarlos", bibliotecaFmp);
        Estudiante estudiante4=new Estudiante("MA26001", "Walter Ivan Monge Avila", "Ingenieria Agronomica", "monge1234", bibliotecaFmp);
        Estudiante estudiante5=new Estudiante("GH27013", "Giselle Noemy Garcia Hernandez", "Ingenieria Agroindustrial", "soyUes_", bibliotecaFmp);        

        bibliotecaFmp.agregarEstudiante(estudiante1);
        bibliotecaFmp.agregarEstudiante(estudiante2);
        bibliotecaFmp.agregarEstudiante(estudiante3);
        bibliotecaFmp.agregarEstudiante(estudiante4);
        bibliotecaFmp.agregarEstudiante(estudiante5);
        
        bibliotecaFmp.registrarBibliotecario(bibliotecario1);
        bibliotecaFmp.registrarBibliotecario(bibliotecario2);
        
        bibliotecaFmp.agregarLibro(libro1);
        bibliotecaFmp.agregarLibro(libro2);
        
        VistaBiblioteca vistaBiblioteca = new VistaBiblioteca(bibliotecaFmp);
        ControladorBiblioteca controladorBiblioteca = new ControladorBiblioteca(vistaBiblioteca, bibliotecaFmp);
        controladorBiblioteca.menuInicioSesion();
    }   
}