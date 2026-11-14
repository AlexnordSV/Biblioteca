package Vista;

import Modelo.Biblioteca;
import Modelo.Bibliotecario;
import Modelo.Cabina;
import Modelo.Computadora;
import Modelo.Estudiante;
import Modelo.Libro;
import Modelo.PrestamoComputadora;
import Modelo.PrestamoLibro;
import Modelo.ReservaCabina;
import java.time.LocalDate;
import java.time.Month;
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
    
    public String pedirCarnet(){
        System.out.println("Sistema bibliotecario, ingresar su CARNET");
        return scanner.nextLine();
    }
    
    public String pedirContrasenia(){
        System.out.println("Ingrese su contraseña: ");
        return scanner.nextLine();
    }
    
// Método auxiliar reutilizable para pedir cualquier entero con validación
public int validarEntero(String mensaje, String mensajeError) {
        int numero = -1;
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine();
            try {
                numero = Integer.parseInt(entrada.trim());
                break; // Si es un número válido, rompemos el ciclo y lo retornamos
            } catch (NumberFormatException e) {
                System.out.println("[Error] " + mensajeError);
            }
        }
        return numero;
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
    System.out.println("          COMPROBANTE DE PRÉSTAMO       \n");
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
    
    public void comprobanteDevolucion(PrestamoLibro prestamo, long dias, LocalDate fin) {
        System.out.println("\n========================================");
        System.out.println("       COMPROBANTE DE DEVOLUCIÓN        \n");
        System.out.println("ID Préstamo:   " + prestamo.getIdPrestamoLibro());
        System.out.println("Estudiante:    " + prestamo.getEstudiante().getNombreCompleto());
        System.out.println("Libro Devuelto:" + prestamo.getLibro().getTitulo() + " | Código: " + prestamo.getLibro().getCodigo());
        System.out.println("F. Límite:     " + prestamo.getFechaDevolucion());
        System.out.println("F. Devolución: " + prestamo.getDevolucionReal());
        System.out.println("----------------------------------------");
        if (dias > 0) {
            System.out.println("[SANCIÓN APLICADA]");
            System.out.println("Días de retraso: " + dias);
            System.out.println("Estás bloqueado para nuevos préstamos hasta: " + fin);
        } else {
            System.out.println("Prestamo realizado con exito!");
        }
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
            System.out.println((i+1) + ". CODIGO: "+lib.getCodigo() + " | TITULO: "+lib.getTitulo()+" | ESTADO: "+lib.getEstado());
        }
       
        System.out.println("=================");
        System.out.println("Opciones: [1] Siguiente pagina | [2] Pagina anterior | [3] Salir");
        System.out.println("Digite una opcion ");
        
        int opc = scanner.nextInt();
        scanner.nextLine();
        return opc;
    }
    
    //Vista prestamo cabina
     public int menuReservaCabina() {
        System.out.println("\n---Apartado de reserva de cabinas---\n");
        System.out.println("1. Realizar reserva de cabina.\n2. Ver horario semanal y disponibilidad de cabinas"
                + "\n3. Ver reserva actual\n4. Regresar al menu anterior");
        System.out.println("DIgite la opción a realizar: ");

        int opc = scanner.nextInt();
        scanner.nextLine();
        return opc;
    }
     
     public String pedirIdCabina(){
         System.out.println("Ingrese el id de la cabina a reservar: ");
         return scanner.nextLine();
     }
     
    public int pedirHoraCabina() {
        System.out.println("¿A qué hora desea iniciar su reserva?");
        System.out.println("Nota: Las reservas son de 1 hora exacta (Ej: ingrese 14 para las 2:00 PM).");
        System.out.print("Digite solo la hora (formato 24h, ej. 8 a 17): ");

        int hora = scanner.nextInt();
        scanner.nextLine();
        return hora;
    }

    public void mostrarComprobanteCabina(ReservaCabina reserva) {
        System.out.println("========================================");
        System.out.println("      COMPROBANTE DE RESERVA CABINA     \n");
        System.out.println("ID Reserva:  " + reserva.getIdPrestamoCabina());
        System.out.println("Estudiante:  " + reserva.getEstudiante().getNombreCompleto());
        System.out.println("Carnet:      " + reserva.getEstudiante().getCarnet());
        System.out.println("Cabina:      " + reserva.getCabina().getNombre() + " (" + reserva.getCabina().getIdCabina() + ")");
        System.out.println("Fecha:       " + reserva.getFecha());
        System.out.println("Horario:     " + reserva.getHoraInicio() + " - " + reserva.getHoraFin() + " hrs");
        System.out.println("Estado:      " + (reserva.isActivo() ? "Activa" : "Finalizada"));
        System.out.println("========================================\n");
    }
   
    public void mostrarListaCabinas(List<Cabina> cabinas) {
        System.out.println("\n==========================================");
        System.out.println("      ESTADO Y DISPONIBILIDAD DE CABINAS  \n");
        for (Cabina c : cabinas) {
            String estado = c.isMantenimiento() ? "Disponible" : "Ocupado";
            System.out.println("Código: " + c.getIdCabina() + " | Nombre: " + c.getNombre()
                    + " | Capacidad: " + c.getCapcaidad() + " pers. " + estado);
        }
        System.out.println("==========================================\n");
    }

    public int pedirDiaSemana() {
        System.out.println("\nSeleccione el día de la semana:");
        System.out.println("1. Lunes");
        System.out.println("2. Martes");
        System.out.println("3. Miércoles");
        System.out.println("4. Jueves");
        System.out.println("5. Viernes");
        System.out.print("Digite una opción (1-5): ");
        int dia = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer
        return dia;
    }

    public void matrizCabina(Cabina cabina, String[][] matriz) {
        System.out.println("========================");
        System.out.println("Matriz horario de: " + cabina.getNombre() + "[" + cabina.getIdCabina() + "]\n");
        System.out.println(String.format("%-10s | %-10s | %-10s | %-10s | %-10s | %-10s",
                "Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"));

        int horaInicio = 8;

        for (int i = 0; i < 8; i++) {
            String rangoDeHora = (horaInicio + i) + "-" + (horaInicio + i + 1);
            System.out.println(String.format("%-10s | %-10s | %-10s | %-10s | %-10s | %-10s",
                    rangoDeHora,
                    matriz[i][0], matriz[i][1], matriz[i][2], matriz[i][3], matriz[i][4]));
        }
        System.out.println("========================");

    }

    // vista prestamo compus
    public int menuPrestamoComputadora() {
        System.out.println("\n---Apartado de prestamo de computadoras---\n");
        System.out.println("1. Realizar prestamo de computadora.\n2. Ver horario semanal y disponibilidad de computadoras"
                + "\n3. Ver reserva actual\n4. Regresar al menu anterior");
        System.out.println("DIgite la opción a realizar: ");

        int opc = scanner.nextInt();
        scanner.nextLine();
        return opc;
    }

    public String pedirIdComputadora() {
        System.out.println("\nIngrese el ID de la computadora: \n");
        return scanner.nextLine();
    }

    public void mostrarListaCompu(List<Computadora> computadoras) {
        System.out.println("\n==========================================");
        System.out.println("      ESTADO Y DISPONIBILIDAD DE PCs  \n");
        for (Computadora Pc : computadoras) {
            String estado = Pc.isMantenimiento() ? "Disponible" : "Ocupado";
            System.out.println("Código: " + Pc.getIdComputadora() + " | Nombre: " + Pc.getEquipo()
                    + " | estado:" + estado);
        }
    }

    public void mostrarComprobanteCompu(PrestamoComputadora prestamo) {
        System.out.println("========================================");
        System.out.println("      COMPROBANTE DE RESERVA PC     \n");
        System.out.println("ID Reserva:  " + prestamo.getIdPrestamoComputadora());
        System.out.println("Estudiante:  " + prestamo.getEstudiante().getNombreCompleto());
        System.out.println("Carnet:      " + prestamo.getEstudiante().getCarnet());
        System.out.println("Computadora: " + prestamo.getComputadora().getEquipo() + " (" + prestamo.getComputadora().getIdComputadora() + " )");
        System.out.println("Fecha:       " + prestamo.getFecha());
        System.out.println("Horario:     " + prestamo.getHoraInicio() + " - " + prestamo.getHoraFin() + " hrs");
        System.out.println("Estado:      " + (prestamo.isActivo() ? "Activa" : "Finalizada"));
        System.out.println("========================================\n");
    }
    
    public void matrizComputadora(Computadora compu, String[][] matriz) {
        System.out.println("========================");
        System.out.println("Matriz horario de: " + compu.getEquipo() + "[" + compu.getIdComputadora() + "]\n");
        System.out.println(String.format("%-10s | %-10s | %-10s | %-10s | %-10s | %-10s",
                "Hora", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes"));

        int horaInicio = 8;

        for (int i = 0; i < 8; i++) {
            String rangoDeHora = (horaInicio + i) + "-" + (horaInicio + i + 1);
            System.out.println(String.format("%-10s | %-10s | %-10s | %-10s | %-10s | %-10s",
                    rangoDeHora,
                    matriz[i][0], matriz[i][1], matriz[i][2], matriz[i][3], matriz[i][4]));
        }
        System.out.println("========================");

    }    
    
    
    
    //BIBLIOTECARIO PARA ABAJO
        public int menuBibliotecario(Bibliotecario bibliotecario) {
        System.out.println("Bienvenido: " + bibliotecario.getNombreCompleto() + " (" + bibliotecario.getUsuario() + ")");
        System.out.println("Menu de administrador/bibliotecario");
        System.out.println("1. Ver inventario general de biblioteca\n2. Ver estanterias\n3. Ver prestamos activos"
                + "\n 4. Libros (modificar, eliminar, mover de estanterias y añadir libros al sistema)\n5-Cerrar sesion");
        System.out.println("Digite la opción a realizar:");

        int opc = scanner.nextInt();
        scanner.nextLine();
        return opc;
    }
}
