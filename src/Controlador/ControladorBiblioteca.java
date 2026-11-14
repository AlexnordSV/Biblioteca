package Controlador;

import Modelo.Biblioteca;
import Modelo.Bibliotecario;
import Modelo.Cabina;
import Modelo.Computadora;
import Modelo.Estudiante;
import Modelo.Libro;
import Modelo.PrestamoComputadora;
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

            String id = vistaBiblioteca.pedirCarnet();
            String password = vistaBiblioteca.pedirContrasenia();
            
            Bibliotecario bibliotecario = biblioteca.validarLoginBibliotecario(id, password);
            if (bibliotecario != null) {
                vistaBiblioteca.mostrarMensaje("\nInicio de sesión valido!\n");
                acceso=true;
                mostrarMenuBibliotecario(bibliotecario);
                break;
            }

            Estudiante estudiante = biblioteca.validarLoginEstudiante(id.toUpperCase(), password);
            if (estudiante != null) {
                vistaBiblioteca.mostrarMensaje("\nInicio de sesión valido!\n");
                acceso=true;
                mostrarMenuEstudiante(estudiante);
                break;
                
            }
            vistaBiblioteca.mostrarMensaje("\nCredenciales no validas\n");
        }

    }
    
    //Modulo estudiante
    
    private void mostrarMenuEstudiante(Estudiante estudiante){
        int opcion=0;
        do{
            opcion=vistaBiblioteca.menuEstudiante(estudiante);
            switch(opcion){
                case 1:
                    vistaBiblioteca.mostrarMensaje("\nAccediendo al menu de prestamos de libros.\n");
                    mostrarMenuLibros(estudiante);
                    break;
                case 2:
                    vistaBiblioteca.mostrarMensaje("\nAccediendo al menu de reserva de cabinas.\n");
                    mostrarMenuCabinas(estudiante);
                    break;
                case 3:
                    vistaBiblioteca.mostrarMensaje("\nAccediendo al menu de prestamo de computadoras.\n");
                    mostrarMenuComputadoras(estudiante);
                    break;
                case 4:
                    vistaBiblioteca.mostrarMensaje("\nCerrando sesión!\n");
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
                    vistaBiblioteca.mostrarMensaje("\nAbriendo menu de prestamo de libros\n");
                    prestamoLibro(estudiante);
                    break;
                case 2:
                    vistaBiblioteca.mostrarMensaje("\nDesplegando coleccion en estanterias... \n");
                    mostrarColeccionLibros();
                    break;
                case 3:
                    vistaBiblioteca.mostrarMensaje("\nConsultando en el sistema si existe un prestamo activo...\n");
                    PrestamoLibro actual = biblioteca.prestamoActivoLibro(estudiante.getCarnet());
                    if(actual != null){
                        vistaBiblioteca.mostrarComprobantePrestamo(actual);
                    }else{
                        vistaBiblioteca.mostrarMensaje("\nSin prestamos activos!!\n");
                    }
                    
                    break;
                case 4:
                    vistaBiblioteca.mostrarMensaje("\nEjecutando la devolucion de libro...\n");
                    devolverLibro(estudiante);
                    break;
                case 5: 
                    vistaBiblioteca.mostrarMensaje("\nVolviendo al menu anterior...\n");
                    mostrarMenuEstudiante(estudiante);
                    break;
                default:
                    System.out.println("\nOpción no valida, ingrese una opción entre 1 y 5\n");
                    break;
                        
            }
        }while(opcion!=5);
    }
    
    
    //modulo prestamo libro
    
    private void prestamoLibro(Estudiante estudiante){
        
        PrestamoLibro prestamoActivo = biblioteca.prestamoActivoLibro(estudiante.getCarnet());
        if(prestamoActivo != null){
            vistaBiblioteca.mostrarMensaje("\n[Error] existe prestamo activo\n");
        }
        if(estudiante.estaBloqueado()){
            vistaBiblioteca.mostrarMensaje("\n[Error] estudiante con bloqueo por sanción en retraso de prestamo"
                    + "puede realizar un nuevo prestamo a partir de la siguiente fecha:  "+estudiante.getFechaFinBloqueo());
            return;
        }
        
        String codigo = vistaBiblioteca.realizarPrestamoLibro();
        Libro libro = biblioteca.buscarLibro(codigo);
        
        if(libro == null){
            vistaBiblioteca.mostrarMensaje("\n[Error] no se encontró ningun libro registrado con: "+codigo);
            return;
        }
        
        if(!libro.isDisponible()){
            vistaBiblioteca.mostrarMensaje("\n[Error] el libro "+libro.getTitulo()+" no esta disponible.\n");
                    return;
        }
        
        vistaBiblioteca.mostrarMensaje("\nLibro encontrado, registrando: "+libro.getTitulo());
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
            vistaBiblioteca.mostrarMensaje("\nNo hay libros registrados\n");
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
                        vistaBiblioteca.mostrarMensaje("\n[Aviso] Ya estás en la última página.\n");
                    }
                    break;

                case 2: // Página anterior
                    if (paginaActual > 1) {
                        paginaActual--;
                    } else {
                        vistaBiblioteca.mostrarMensaje("\n[Aviso] Ya estás en la primera página.\n");
                    }
                    break;

                case 3: // Salir
                    vistaBiblioteca.mostrarMensaje("\nSaliendo del catálogo...\n");
                    break;

                default:
                    vistaBiblioteca.mostrarMensaje("\n[Error] Opción no válida.\n");
            }

        } while (opcion != 3 && !libros.isEmpty());
    }
    
    private void devolverLibro(Estudiante estudiante){
        PrestamoLibro activo = biblioteca.prestamoActivoLibro(estudiante.getCarnet());
        
        if(activo == null){
            vistaBiblioteca.mostrarMensaje("\n[Error] no hay ningun prestamo activo\n");
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
                    vistaBiblioteca.mostrarMensaje("\nCargando la interfaz de reserva de cabina\n");
                    realizarReservaCabina(estudiante);
                    break;
                case 2:
                    vistaBiblioteca.mostrarMensaje("\nObteniendo matriz de horarios...\n");
                    String id = vistaBiblioteca.pedirIdCabina();
                    Cabina cabinaConsulta = biblioteca.buscarCabina(id);

                    if(cabinaConsulta == null){
                        vistaBiblioteca.mostrarMensaje("\n[Error] La cabina ingresada no existe.\n");
                        break;
                    }
                    
                    int diaConsulta = vistaBiblioteca.pedirDiaSemana();
                    
                    String [][] matrizHoraria = biblioteca.matrizHorarioCabina(cabinaConsulta, diaConsulta);
                    vistaBiblioteca.matrizCabina(cabinaConsulta, matrizHoraria);
                    
                    break;
                case 3:
                    vistaBiblioteca.mostrarMensaje("\nObteniendo préstamo actual...\n");
                    reservaActualCabina(estudiante); 
                    break;
                case 4:
                    vistaBiblioteca.mostrarMensaje("\nVolviendo al menú anterior...\n");
                    mostrarMenuEstudiante(estudiante);
                    break; 
                default:
                    vistaBiblioteca.mostrarMensaje("\n[Error] opción no válida, vuelva a intentar.\n");
                    break;
            }
        } while (opc != 4);
    }

    
    private void realizarReservaCabina(Estudiante estudiante){
        ReservaCabina reservaExistente = biblioteca.reservaActivaCabina(estudiante.getCarnet());
        if(reservaExistente != null){
            vistaBiblioteca.mostrarMensaje("\n[Error] ya posee una reserva activa de cabina!!\n");
            return; 
        }
        
        vistaBiblioteca.mostrarListaCabinas(biblioteca.getListaCabinas());
        
        String idCabina = vistaBiblioteca.pedirIdCabina();
        Cabina cabina = biblioteca.buscarCabina(idCabina);
    
        if (cabina == null){
            vistaBiblioteca.mostrarMensaje("\n[Error], cabina no existe.\n");
            return;
        }
        
        if(!cabina.isMantenimiento()){
            vistaBiblioteca.mostrarMensaje("\n[Error] la cabina no esta disponible\n");
            return;
        }
        
        int opcionDia = vistaBiblioteca.pedirDiaSemana();
        if(opcionDia < 1 || opcionDia > 5){
            vistaBiblioteca.mostrarMensaje("\n[Error] dia no valido\n");
            return;
        }
        
        int hora = vistaBiblioteca.pedirHoraCabina();
        if (hora < 8 || hora > 16) {
            vistaBiblioteca.mostrarMensaje("\n[Error] su hora esta fuera del horario de 8:00-16:00\n");
            return;
        }
        
        String[][] matrizVerifica = biblioteca.matrizHorarioCabina(cabina, opcionDia);
        int filaHora = hora - 8;
        if(matrizVerifica[filaHora][opcionDia - 1].equalsIgnoreCase("Ocupado")){
            vistaBiblioteca.mostrarMensaje("[Error] este horario ya está reservado.");
            return;
        }
         
        String id = "RC-"+System.currentTimeMillis();
        LocalDate hoy = LocalDate.now();
        int diaActual = hoy.getDayOfWeek().getValue();
        if(diaActual > 5){
            diaActual = 5;
        }
        
        int diasDiferencia = (opcionDia - diaActual);
        
        if(diasDiferencia <= 0){
            diasDiferencia +=7;
        }
        
        if(diasDiferencia > 7){
            vistaBiblioteca.mostrarMensaje("\n[Error] solo se pueden realizar reservas con un maximo de 7 dias de anticipacion \n");
            return;
        }
        
        LocalDate fechaReserva = hoy.plusDays(diasDiferencia);
        
        LocalTime horaInicio = LocalTime.of(hora, 0);
        LocalTime horaFin = horaInicio.plusHours(1);
        
        ReservaCabina reserva = new ReservaCabina(id, fechaReserva, horaInicio, horaFin, true, cabina, estudiante);
        cabina.setMantenimiento(false);

        biblioteca.agregarReservaCabina(reserva);
        vistaBiblioteca.mostrarComprobanteCabina(reserva);
    }
    
    private void reservaActualCabina(Estudiante estudiante) {
        ReservaCabina reserva = biblioteca.reservaActivaCabina(estudiante.getCarnet());

        if (reserva != null) {
            vistaBiblioteca.mostrarComprobanteCabina(reserva);
        } else {
            vistaBiblioteca.mostrarMensaje("\nNo tienes ninguna reserva de cabina activa en este momento.\n");
        }
    }    
    
    private void mostrarMenuComputadoras(Estudiante estudiante) {
        int opc = 0;
        do {
            opc = vistaBiblioteca.menuPrestamoComputadora(); 
            switch (opc) {
                case 1:
                    vistaBiblioteca.mostrarMensaje("\nCargando la interfaz de prestamo de computadora...\n");
                    realizarPrestamoComputadora(estudiante);
                    break;
                case 2:
                    vistaBiblioteca.mostrarMensaje("\nObteniendo matriz de horarios...\n");
                    String id = vistaBiblioteca.pedirIdComputadora();
                    Computadora compuConsulta = biblioteca.buscarCompu(id);

                    if(compuConsulta == null){
                        vistaBiblioteca.mostrarMensaje("\n[Error] La computadora ingresada no existe.\n");
                        break;
                    }
                    
                    int diaConsulta = vistaBiblioteca.pedirDiaSemana();
                    
                    String [][] matrizHoraria = biblioteca.obtenerMatrizHorarioComputadora(compuConsulta, diaConsulta);
                    vistaBiblioteca.matrizComputadora(compuConsulta, matrizHoraria);
                    
                    
                    break;
                case 3:
                    vistaBiblioteca.mostrarMensaje("\nObteniendo préstamo actual...\n");
                    PrestamoComputadora prestamo = biblioteca.prestamoActivoCompu(estudiante.getCarnet());
                    if(prestamo != null){
                        vistaBiblioteca.mostrarComprobanteCompu(prestamo);
                    }else{
                        vistaBiblioteca.mostrarMensaje("\n[Error] sin prestamo activo\n");
                    }
                    break;
                case 4:
                    vistaBiblioteca.mostrarMensaje("\nVolviendo al menú anterior...\n");
                    mostrarMenuEstudiante(estudiante);
                    break;
                default:
                    vistaBiblioteca.mostrarMensaje("\n[Error] opción no válida, vuelva a intentar.\n");
                    break;
            }
        } while (opc != 4);
    }

    private void realizarPrestamoComputadora(Estudiante estudiante) {
  
        PrestamoComputadora prestamoExistente = biblioteca.prestamoActivoCompu(estudiante.getCarnet());
        if (prestamoExistente != null) {
            vistaBiblioteca.mostrarMensaje("[Error] Ya posees un préstamo activo de computadora (" + prestamoExistente.getComputadora().getIdComputadora() + ").");
            return;
        }

        // 2. Mostrar listado y solicitar ID del equipo
        vistaBiblioteca.mostrarListaCompu(biblioteca.getListaComputadoras());
        String idComputadora = vistaBiblioteca.pedirIdComputadora();
        Computadora computadora = biblioteca.buscarCompu(idComputadora);

        if (computadora == null) {
            vistaBiblioteca.mostrarMensaje("[Error], la computadora no existe.");
            return;
        }

        // 3. Validar si la computadora está en mantenimiento físico
        if (computadora.isMantenimiento()) {
            vistaBiblioteca.mostrarMensaje("[Error] La computadora se encuentra en mantenimiento y no admite préstamos.");
            return;
        }

        // 4. Solicitar el día de la semana (1 = Lunes, ..., 5 = Viernes)
        int opcionDia = vistaBiblioteca.pedirDiaSemana();
        if (opcionDia < 1 || opcionDia > 5) {
            vistaBiblioteca.mostrarMensaje("[Error] Día no válido.");
            return;
        }

        // 5. Lógica de cálculo de fecha y ventana de 7 días de anticipación
        LocalDate hoy = LocalDate.now();
        int diaActualValor = hoy.getDayOfWeek().getValue();
        if (diaActualValor > 5) {
            diaActualValor = 5; // Por si se accede en fin de semana
        }
        int diasDeDiferencia = opcionDia - diaActualValor;
        if (diasDeDiferencia <= 0) {
            diasDeDiferencia += 7; // Si el día ya pasó esta semana, pasa a la siguiente
        }

        if (diasDeDiferencia > 7) {
            vistaBiblioteca.mostrarMensaje("[Error] Solo puedes realizar préstamos con un máximo de 7 días de anticipación.");
            return;
        }

        LocalDate fechaPrestamo = hoy.plusDays(diasDeDiferencia);

        // 6. Solicitar hora exacta (8:00 a 16:00)
        int hora = vistaBiblioteca.pedirHoraCabina(); // Puedes reutilizar el método de hora
        if (hora < 8 || hora > 16) {
            vistaBiblioteca.mostrarMensaje("[Error] Su hora está fuera del horario de atención (8:00 - 16:00).");
            return;
        }

        // 7. Verificar en la matriz si la hora seleccionada ya está ocupada
        String[][] matrizVerificacion = biblioteca.obtenerMatrizHorarioComputadora(computadora, opcionDia);
        int filaHora = hora - 8;
        if (matrizVerificacion[filaHora][opcionDia - 1].equalsIgnoreCase("Ocupado")) {
            vistaBiblioteca.mostrarMensaje("[Error] Lo sentimos, este horario ya se encuentra ocupado para este equipo.");
            return;
        }

        // 8. Generar y registrar el préstamo de forma exitosa
        String id = "PCMP-" + System.currentTimeMillis();
        LocalTime horaInicio = LocalTime.of(hora, 0);
        LocalTime horaFin = horaInicio.plusHours(1);

        PrestamoComputadora prestamo = new PrestamoComputadora(id, fechaPrestamo, horaInicio, horaFin, true, computadora, estudiante);

        biblioteca.agregarPrestamoCompu(prestamo);
        vistaBiblioteca.mostrarComprobanteCompu(prestamo);
    }
    
    //ESTUDIANTE PARA ARRIBA
    ///BIBLIOTECARIO PARA ABAJO
    private void mostrarMenuBibliotecario(Bibliotecario bibliotecario){
        vistaBiblioteca.menuBibliotecario(bibliotecario);
    }

    public void controladorMostrarBiblioteca(){

    }
}