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
        Libro libro3=new Libro("ISBN-1234-0724", "La Odisea", "Homero", 553, "UESLIB-003");
        Libro libro4=new Libro("ISBN-9324-2348", "La Divina Comedia", "Dante Aligeri", 249, "UESLIB-004");
        Libro libro5=new Libro("ISBN-1111-2332", "1984", "George Orwell", 211, "UESLIB-005");
        Libro libro6=new Libro("ISBN-4500-5000", "La Metamorfosis", "Franz Kafka", 65, "UESLIB-006");
        Libro libro7=new Libro("ISBN-0342-1234", "Crimen y Castigo", "Fiodor Dostoyevsky", 499, "UESLIB-007");
        Libro libro8=new Libro("ISBN-0342-1234", "Administracion, una perspectiva global y globalizada", "Harol Kuntz", 788, "UESLIB-008");
        Libro libro9=new Libro("ISBN-0342-1234", "Rayuelas", "Julio Cortazar", 653, "UESLIB-009");
        Libro libro10=new Libro("ISBN-0342-1234", "Pensamiento Marxista e historia del comunismo en latinoamerica", "Antonio Gallardez", 269, "UESLIB-010");
        Libro libro11=new Libro("ISBN-0342-1234", "Historia general de El Salvador", "Andres Aparicio", 192, "UESLIB-011");
        
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
        bibliotecaFmp.agregarLibro(libro3);
        bibliotecaFmp.agregarLibro(libro4);
        bibliotecaFmp.agregarLibro(libro5);
        bibliotecaFmp.agregarLibro(libro6);
        bibliotecaFmp.agregarLibro(libro7);
        bibliotecaFmp.agregarLibro(libro8);
        bibliotecaFmp.agregarLibro(libro9);
        bibliotecaFmp.agregarLibro(libro10);
        bibliotecaFmp.agregarLibro(libro11);
        
        
        VistaBiblioteca vistaBiblioteca = new VistaBiblioteca(bibliotecaFmp);
        ControladorBiblioteca controladorBiblioteca = new ControladorBiblioteca(vistaBiblioteca, bibliotecaFmp);
        controladorBiblioteca.menuInicioSesion();
    }   
}