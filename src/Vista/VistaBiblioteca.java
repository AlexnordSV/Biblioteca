package Vista;

import Modelo.Biblioteca;
import Modelo.Estudiante;
import java.util.Scanner;

public class VistaBiblioteca {
    private Biblioteca biblioteca;
    private Scanner scanner =new Scanner(System.in);

    public VistaBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
    
    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
    
    public String pedirID(){
        System.out.println("Sistema bibliotecario, ingresar su CARNET");
        return scanner.nextLine();
    }
    
    public String pedirContrasenia(){
        System.out.println("Ingrese su contraseña: ");
        return scanner.nextLine();
    }
    
    
    
    private void mostrarDetallesBiblioteca(Biblioteca biblioteca){
        System.out.println("=====INFORMACION GENERAL=====");
        System.out.println("BIBLIOTECA: "+biblioteca.getSede());
        System.out.println("CORREO: "+biblioteca.getCorreo());
        System.out.println("TELEFONO: "+biblioteca.getTelefono());
        System.out.println("=============================");
    }
    
    public void menuEstudiante(Estudiante estudiante){
        System.out.println("Bienvenido "+estudiante.getNombreCompleto());
        System.out.println("Menu de prestamos.\n1-Realizar prestamo libro.\n2-Realizar prestamo cabina\n3-Realizar prestamo computadora.");
        System.out.println("Digite la opción a realizar: ");
        int opc = scanner.nextInt();
    }
}
