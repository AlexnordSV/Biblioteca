package Controlador;

import Modelo.Biblioteca;
import Modelo.Bibliotecario;
import Modelo.Cabina;
import Modelo.Estudiante;
import Modelo.Libro;
import Modelo.PrestamoLibro;
import Modelo.ReservaCabina;
import Vista.VistaBiblioteca;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

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
    
    //Modulo estudiante
    
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
                    vistaBiblioteca.mostrarMensaje("\nAccediendo al menu de reserva de cabinas.");
                    mostrarMenuCabinas(estudiante);
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
                    mostrarColeccionLibros();
                    break;
                case 3:
                    vistaBiblioteca.mostrarMensaje("Consultando en el sistema si existe un prestamo activo...");
                    PrestamoLibro actual = biblioteca.prestamoActivoLibro(estudiante.getCarnet());
                    if(actual != null){
                        vistaBiblioteca.mostrarComprobantePrestamo(actual);
                    }else{
                        vistaBiblioteca.mostrarMensaje("Sin prestamos activos!!");
                    }
                    
                    break;
                case 4:
                    vistaBiblioteca.mostrarMensaje("Ejecutando la devolucion de libro...");
                    devolverLibro(estudiante);
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
    
    
    //modulo prestamo libro
    
    private void prestamoLibro(Estudiante estudiante){
        
        PrestamoLibro prestamoActivo = biblioteca.prestamoActivoLibro(estudiante.getCarnet());
        if(prestamoActivo != null){
            vistaBiblioteca.mostrarMensaje("[Error] existe prestamo activo");
        }
        if(estudiante.estaBloqueado()){
            vistaBiblioteca.mostrarMensaje("[Error] estudiante con bloqueo por sanción en retraso de prestamo"
                    + "puede realizar un nuevo prestamo a partir de la siguiente fecha:  "+estudiante.getFechaFinBloqueo());
            return;
        }
        
        String codigo = vistaBiblioteca.realizarPrestamoLibro();
        Libro libro = biblioteca.buscarLibro(codigo);
        
        if(libro == null){
            vistaBiblioteca.mostrarMensaje("[Error] no se encontró ningun libro registrado con: "+codigo);
            return;
        }
        
        if(!libro.isDisponible()){
            vistaBiblioteca.mostrarMensaje("[Error] el libro "+libro.getTitulo()+" no esta disponible.");
                    return;
        }
        
        vistaBiblioteca.mostrarMensaje("Libro encontrado, registrando: "+libro.getTitulo());
        String idPrestamo = "P-" + System.currentTimeMillis();
        PrestamoLibro newPrestamo = new PrestamoLibro(idPrestamo, estudiante, libro);
        
        libro.setDisponible(false);
        libro.setEstado("Prestado");
        biblioteca.agregarPrestamoLibro(newPrestamo);
        vistaBiblioteca.mostrarComprobantePrestamo(newPrestamo);
    }
    
    private void mostrarColeccionLibros() {
        List<Libro> libros = biblioteca.obtenerLibros();
        if (libros.isEmpty()) {
            vistaBiblioteca.mostrarMensaje("No hay libros registrados");
            return;
        }

        int libroCadaPagina = 5;
        int totalPaginas = (int) Math.ceil((double) libros.size() / libroCadaPagina);
        int paginaActual = 1;
        int opcion = 0;

        do {
            // Calcular los índices de inicio y fin para los 5 libros de la página actual
            int inicio = (paginaActual - 1) * libroCadaPagina;
            int fin = Math.min(inicio + libroCadaPagina, libros.size());

            // Obtener solo la sub-lista de esos 5 libros
            List<Libro> librosPagina = libros.subList(inicio, fin);

            // Mostrar la vista y capturar la acción del usuario
            opcion = vistaBiblioteca.mostrarLibros(librosPagina, paginaActual, totalPaginas);

            switch (opcion) {
                case 1: // Siguiente página
                    if (paginaActual < totalPaginas) {
                        paginaActual++;
                    } else {
                        vistaBiblioteca.mostrarMensaje("\n[Aviso] Ya estás en la última página.");
                    }
                    break;

                case 2: // Página anterior
                    if (paginaActual > 1) {
                        paginaActual--;
                    } else {
                        vistaBiblioteca.mostrarMensaje("\n[Aviso] Ya estás en la primera página.");
                    }
                    break;

                case 3: // Salir
                    vistaBiblioteca.mostrarMensaje("Saliendo del catálogo...");
                    break;

                default:
                    vistaBiblioteca.mostrarMensaje("[Error] Opción no válida.");
            }

        } while (opcion != 3 && !libros.isEmpty());
    }
    
    private void devolverLibro(Estudiante estudiante){
        PrestamoLibro activo = biblioteca.prestamoActivoLibro(estudiante.getCarnet());
        
        if(activo == null){
            vistaBiblioteca.mostrarMensaje("[Error] no hay ningun prestamo activo");
            return;
        }
        
        LocalDate hoy = LocalDate.now();
        activo.setDevolucionReal(hoy);
        activo.setEstadoPrestamo("Devuelto");
        
        Libro libro = activo.getLibro();
        libro.setDisponible(true);
        
        long diasRetrasado = 0;
        if(hoy.isAfter(activo.getFechaDevolucion())){
            diasRetrasado = ChronoUnit.DAYS.between(activo.getFechaDevolucion(), hoy);
            
            LocalDate finBloqueo = hoy.plusDays(diasRetrasado);
            estudiante.setFechaFinBloqueo(finBloqueo);
        }else{
            estudiante.setFechaFinBloqueo(hoy);
        }
        vistaBiblioteca.comprobanteDevolucion(activo, diasRetrasado, hoy);
    }
    
    //Modulo de cabina
    private void mostrarMenuCabinas(Estudiante estudiante) {
        int opc = 0;
        do {
            opc = vistaBiblioteca.menuReservaCabina(); 
            switch (opc) {
                case 1:
                    vistaBiblioteca.mostrarMensaje("Cargando la interfaz de reserva de cabina");
                    realizarReservaCabina(estudiante);
                    break;
                case 2:
                    vistaBiblioteca.mostrarMensaje("Obteniendo matriz de horarios...");
                    String id = vistaBiblioteca.pedirIdCabina();
                    Cabina cabinaConsulta = biblioteca.buscarCabina(id);

                    if(cabinaConsulta == null){
                        vistaBiblioteca.mostrarMensaje("[Error] La cabina ingresada no existe.");
                        break;
                    }
                    
                    String [][] matrizHoraria = biblioteca.matrizHorarioCabina(cabinaConsulta);
                    vistaBiblioteca.matrizCabina(cabinaConsulta, matrizHoraria);
                    
                    break;
                case 3:
                    vistaBiblioteca.mostrarMensaje("Obteniendo préstamo actual...");
                    reservaActualCabina(estudiante); 
                    break;
                case 4:
                    vistaBiblioteca.mostrarMensaje("Volviendo al menú anterior...");
                    break; 
                default:
                    vistaBiblioteca.mostrarMensaje("[Error] opción no válida, vuelva a intentar.");
                    break;
            }
        } while (opc != 4);
    }

    
    private void realizarReservaCabina(Estudiante estudiante){
        vistaBiblioteca.mostrarListaCabinas(biblioteca.getListaCabinas());
        
        String idCabina = vistaBiblioteca.pedirIdCabina();
        Cabina cabina = biblioteca.buscarCabina(idCabina);
    
        if (cabina == null){
            vistaBiblioteca.mostrarMensaje("[Error], cabina no existe.");
            return;
        }
        
        if(!cabina.isDisponible()){
            vistaBiblioteca.mostrarMensaje("[Error] la cabina no esta disponible");
            return;
        }
        
        int opcionDia = vistaBiblioteca.pedirDiaSemana();
        if(opcionDia < 1 || opcionDia > 5){
            vistaBiblioteca.mostrarMensaje("[Error] dia no valido");
            return;
        }
        
        int hora = vistaBiblioteca.pedirHoraCabina();
        if (hora < 8 || hora > 16) {
            vistaBiblioteca.mostrarMensaje("[Error] su hora esta fuera del horario de 8:00-16:00");
            return;
        }
        
        String id = "RC-"+System.currentTimeMillis();
        LocalDate hoy = LocalDate.now();
        int diaActual = hoy.getDayOfWeek().getValue();
        LocalDate fechaReserva = hoy.plusDays(opcionDia - diaActual);
        
        LocalTime horaInicio = LocalTime.of(hora, 0);
        LocalTime horaFin = horaInicio.plusHours(1);
        
        ReservaCabina reserva = new ReservaCabina(id, fechaReserva, horaInicio, horaFin, true, cabina, estudiante);
        cabina.setDisponible(false);

        biblioteca.agregarReservaCabina(reserva);
        vistaBiblioteca.mostrarComprobanteCabina(reserva);
    }
    
    private void reservaActualCabina(Estudiante estudiante) {
        ReservaCabina reserva = biblioteca.reservaActivaCabina(estudiante.getCarnet());

        if (reserva != null) {
            vistaBiblioteca.mostrarComprobanteCabina(reserva);
        } else {
            vistaBiblioteca.mostrarMensaje("No tienes ninguna reserva de cabina activa en este momento.");
        }
    }    
    
    
    //ESTUDIANTE PARA ARRIBA
    ///BIBLIOTECARIO PARA ABAJO
    private void mostrarMenuBibliotecario(Bibliotecario bibliotecario){
    
    }

    public void controladorMostrarBiblioteca(){

    }
}