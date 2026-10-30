package Vista;

import Modelo.Biblioteca;
import Modelo.Bibliotecario;
import Modelo.Estudiante;
import Modelo.Libro;
import Modelo.PrestamoLibro;
import java.util.List;
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
        
        int opc=scanner.nextInt();
        scanner.nextLine();
        return opc;
    }
    
    public int menuPrestamoLibro(){
        System.out.println("Apartado de prestamo libros");
        System.out.println("1. Realizar prestamo libro.\n2. Ver coleccion de libros"
                + "\n3. Ver prestamos actuales\n4. Devolver libro\n5. Regresar al menu anterior");
        System.out.println("DIgite la opción a realizar: ");
        
        int opc=scanner.nextInt();
        scanner.nextLine();
        return opc;
    }
    
    public String realizarPrestamoLibro(){
        System.out.println("Ingrese el código UESLIB del libro para realizar el prestamo");
        return scanner.nextLine();
    }
    
    public void mostrarComprobantePrestamo(PrestamoLibro prestamo) {
    System.out.println("\n========================================");
    System.out.println("          COMPROBANTE DE PRÉSTAMO       ");
    System.out.println("========================================");
    System.out.println("ID Préstamo: " + prestamo.getIdPrestamoLibro());
    System.out.println("Estudiante:  " + prestamo.getEstudiante().getNombreCompleto());
    System.out.println("Carnet:      " + prestamo.getEstudiante().getCarnet());
    System.out.println("Libro:       " + prestamo.getLibro().getTitulo());
    System.out.println("Código UES:  " + prestamo.getLibro().getCodigo());
    System.out.println("F. Inicio:   " + prestamo.getFechaInicio());
    System.out.println("F. Límite:   " + prestamo.getFechaDevolucion() + " (3 días hábiles)");
    System.out.println("Estado:      " + prestamo.getEstadoPrestamo());
    System.out.println("========================================");
    }
    
    public int mostrarLibros(List<Libro> libros, int pActu, int totPag){
        System.out.println("CATALOGO DE LIBROS (Pagina. "+pActu+"/"+totPag+")");
        
        if(libros.isEmpty()){
            System.out.println("No hay libros registrados.");
            return 2;
        }
        
        for(int i=0; i<libros.size(); i++){
            Libro lib= libros.get(i);
            String estado = lib.isDisponible() ? "Disponible" : "Prestado";
            System.out.println((i+1) + ". codigo: "+lib.getCodigo() + "| titulo: "+lib.getTitulo()+" estado: "+lib.getEstado());
        }
       
        System.out.println("=================");
        System.out.println("Opciones: [1] Siguiente pagina | [2] Pagina anterior | [3] Salir");
        System.out.println("Digite una opcion ");
        
        int opc = scanner.nextInt();
        scanner.nextLine();
        return opc;
    }
    
    
    //De aqui para abajo es la vista del bibliotecario
    
    public int menuBibliotecario(Bibliotecario bibliotecario){
        System.out.println("Bienvenido: "+bibliotecario.getNombreCompleto()+" ("+bibliotecario.getUsuario()+")");
        System.out.println("Menu de administrador/bibliotecario");
        System.out.println("1. Ver inventario general de biblioteca\n2. Ver estanterias\n3. Ver prestamos activos"
                + "\n 4. Libros (modificar, eliminar, mover de estanterias y añadir libros al sistema)\n5-Cerrar sesion");
        System.out.println("Digite la opción a realizar:");
        
        int opc=scanner.nextInt();
        scanner.nextLine();
        return opc;
    }
}
