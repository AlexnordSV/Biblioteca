package Vista;

import Modelo.Biblioteca;
import Modelo.Bibliotecario;
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
    
    public int menuEstudiante(Estudiante estudiante){
        System.out.println("Bienvenido "+estudiante.getNombreCompleto());
        System.out.println("Menu de prestamos.\n1. Menu prestamo libro.\n2. Menu prestamo cabina\n3. Menu prestamo computadora.\n4. Cerrar sesion");
        System.out.println("Digite la opción a realizar: ");
        return scanner.nextInt();
    }
    
    public int menuBibliotecario(Bibliotecario bibliotecario){
        System.out.println("Bienvenido: "+bibliotecario.getNombreCompleto()+" ("+bibliotecario.getUsuario()+")");
        System.out.println("Menu de administrador/bibliotecario");
        System.out.println("1. Ver inventario general de biblioteca\n2. Ver estanterias\n3. Ver prestamos activos"
                + "\n 4. Libros (modificar, eliminar, mover de estanterias y añadir libros al sistema)\n5-Cerrar sesion");
        System.out.println("Digite la opción a realizar:");
        
        
        return scanner.nextInt();
    }
}
