package Modelo;

import Modelo.PrestamoLibro;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Biblioteca {
    private String sede;
    private String telefono;
    private String correo;
    private List<Estanteria> listaEstanteria = new ArrayList<>();
    private List<Cabina> listaCabinas = new ArrayList<>();
    private List<Computadora> listaComputadoras = new ArrayList<>();
    private HashMap<String, Estudiante> mapaEstudiantes = new HashMap<>();
    private HashMap<String, Bibliotecario> mapaBibliotecarios = new HashMap<>();
    private HashMap<String, Libro> mapaLibros = new HashMap<>();
    private HashMap<String, PrestamoLibro> mapaPrestamoLibro = new HashMap<>();
    private HashMap<String, ReservaCabina> mapaReservaCabina = new HashMap<>();
    private HashMap<String, PrestamoComputadora> mapaPrestamoComputadora = new HashMap<>();
    public void mostrarEmpleados(){}
    public void mostrarComputadoras(){}
    public void mostrarCabinas(){}
    public void mostrarEstanterias(){}
    
    //

    public Biblioteca() {
    }
    
    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public List<Estanteria> getListaEstanteria() {
        return listaEstanteria;
    }

    public void setListaEstanteria(List<Estanteria> listaEstanteria) {
        this.listaEstanteria = listaEstanteria;
    }

    public List<Cabina> getListaCabinas() {
        return listaCabinas;
    }

    public void setListaCabinas(List<Cabina> listaCabinas) {
        this.listaCabinas = listaCabinas;
    }

    public List<Computadora> getListaComputadoras() {
        return listaComputadoras;
    }

    public void setListaComputadoras(List<Computadora> listaComputadoras) {
        this.listaComputadoras = listaComputadoras;
    }

    public HashMap<String, Bibliotecario> getMapaBibliotecarios() {
        return mapaBibliotecarios;
    }

    public void setMapaBibliotecarios(HashMap<String, Bibliotecario> mapaBibliotecarios) {
        this.mapaBibliotecarios = mapaBibliotecarios;
    }

    public HashMap<String, Estudiante> getMapaEstudiantes() {
        return mapaEstudiantes;
    }

    public void setMapaEstudiantes(HashMap<String, Estudiante> mapaEstudiantes) {
        this.mapaEstudiantes = mapaEstudiantes;
    }
    //
    //
    //
    public void agregarEstudiante(Estudiante estudiante) {
        mapaEstudiantes.put(estudiante.getCarnet(), estudiante);
    }
    
    public void registrarBibliotecario(Bibliotecario bibliotecario){
        mapaBibliotecarios.put(bibliotecario.getCorreo(), bibliotecario);
    }
    
    public void agregarLibro(Libro libro){
        mapaLibros.put(libro.getCodigo(), libro);
    }
    
    public void agregarPrestamoLibro(PrestamoLibro prestamo){
        mapaPrestamoLibro.put(prestamo.getIdPrestamoLibro(), prestamo);
    }
    
    public void agregarReservaCabina(ReservaCabina reserva){
        mapaReservaCabina.put(reserva.getIdPrestamoCabina(), reserva);
    }
    
    public void agregarCabina(Cabina cabina){
        this.listaCabinas.add(cabina);
    }
    
    public void agregarPrestamoCompu(PrestamoComputadora prestamo){
        mapaPrestamoComputadora.put(prestamo.getIdPrestamoComputadora(), prestamo);
    }
    
    public void agregarComputadora(Computadora compu){
        this.listaComputadoras.add(compu);
    }
    
    public PrestamoLibro prestamoActivoLibro(String carnet) {
        for (PrestamoLibro p : mapaPrestamoLibro.values()) {
            if (p.getEstudiante().getCarnet().equalsIgnoreCase(carnet)
                    && p.getEstadoPrestamo().equalsIgnoreCase("Prestado")) {
                return p;
            }
        }
        return null;
    }    
    
    public ReservaCabina reservaActivaCabina(String carnet) {
        for (ReservaCabina r : mapaReservaCabina.values()) {
            if (r.getEstudiante().getCarnet().equalsIgnoreCase(carnet) && r.isActivo()) {
                return r;
            }
        }
        return null;
    }
    
    public PrestamoComputadora prestamoActivoCompu(String carnet) {
        for (PrestamoComputadora p : mapaPrestamoComputadora.values()) {
            if (p.getEstudiante().getCarnet().equalsIgnoreCase(carnet) && p.isActivo()) {
                return p;
            }
        }
        return null;
    }
    
    public Estudiante validarLoginEstudiante(String carnet, String contrasenia) {
        Estudiante estudiante = mapaEstudiantes.get(carnet);
        if (estudiante != null && estudiante.getContrasenia().equals(contrasenia)) {
            return estudiante;
        }
        return null; 
    }

    public Bibliotecario validarLoginBibliotecario(String usuario, String contrasenia) {
        Bibliotecario bibliotecario = mapaBibliotecarios.get(usuario);
        if (bibliotecario != null && bibliotecario.getContrasenia().equals(contrasenia)) {
            return bibliotecario;
        }
        return null;
    }

    public Libro buscarLibro(String codigo){
        return mapaLibros.get(codigo);
    }
    
    public List<Libro> obtenerLibros(){
        List<Libro> listaLibros = new ArrayList<>(mapaLibros.values());
        Collections.shuffle(listaLibros);//Los saca de manera desordenada
        return listaLibros;
    }
    
    public Cabina buscarCabina(String idCabina){
        for(Cabina cabina : this.listaCabinas){
            if(cabina.getIdCabina().toUpperCase().equals(idCabina.toUpperCase()))
                return cabina;
        }
        return null;
    }
    
    public Computadora buscarCompu(String idComputadora){
        for(Computadora compu : this.listaComputadoras){
            if(compu.getIdComputadora().toUpperCase().equalsIgnoreCase(idComputadora.toUpperCase()))
                return compu;
        }
        return null;
    }
    
    public String [][] matrizHorarioCabina(Cabina cabina, int diaSeleccion){
        String[][] matriz = new String[8][5];
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = "Libre";
            }
        }
        
        LocalDate hoy = LocalDate.now();
        int diaActual = hoy.getDayOfWeek().getValue();
        if(diaActual > 5){
            diaActual = 5;
        }
        
        int diferencia = diaSeleccion - diaActual;
        if(diferencia <= 0){
            diferencia +=7;
        }
        
        LocalDate fechaReal = hoy.plusDays(diferencia);
        
        java.time.temporal.WeekFields semana = java.time.temporal.WeekFields.of(java.util.Locale.getDefault());
        int semanaConsultada = fechaReal.get(semana.weekOfWeekBasedYear());
        
        for (ReservaCabina reserva : mapaReservaCabina.values()) {
            if (reserva.isActivo() && reserva.getCabina().getIdCabina().equalsIgnoreCase(cabina.getIdCabina())) {

                int semanaReserva = reserva.getFecha().get(semana.weekOfWeekBasedYear());

                if (semanaReserva == semanaConsultada) {
                    int horaInicio = reserva.getHoraInicio().getHour();
                    int fila = horaInicio - 8;

                    java.time.DayOfWeek diaSemana = reserva.getFecha().getDayOfWeek();
                    int columna = diaSemana.getValue() - 1;

                    if (fila >= 0 && fila < 8 && columna >= 0 && columna < 5) {
                        matriz[fila][columna] = "Ocupado";
                    }
                }
            }
        }
        return matriz;
    }
    
    public String[][] obtenerMatrizHorarioComputadora(Computadora computadora, int diaConsultaSeleccionado) {
        String[][] matriz = new String[8][5];

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = "Libre";
            }
        }

        LocalDate hoy = LocalDate.now();
        int diaActualValor = hoy.getDayOfWeek().getValue();
        if (diaActualValor > 5) {
            diaActualValor = 5;
        }

        int diferencia = diaConsultaSeleccionado - diaActualValor;
        if (diferencia <= 0) {
            diferencia += 7; 
        }
        LocalDate fechaConsultada = hoy.plusDays(diferencia);

        java.time.temporal.WeekFields weekFields = java.time.temporal.WeekFields.of(java.util.Locale.getDefault());
        int semanaConsultada = fechaConsultada.get(weekFields.weekOfWeekBasedYear());

        for (PrestamoComputadora p : mapaPrestamoComputadora.values()) {
            if (p.isActivo() && p.getComputadora().getIdComputadora().equalsIgnoreCase(computadora.getIdComputadora())) {

                int semanaPrestamo = p.getFecha().get(weekFields.weekOfWeekBasedYear());

                if (semanaPrestamo == semanaConsultada) {
                    int horaInicio = p.getHoraInicio().getHour();
                    int fila = horaInicio - 8; 
                    int columna = p.getFecha().getDayOfWeek().getValue() - 1; 
                    
                    if (fila >= 0 && fila < 8 && columna >= 0 && columna < 5) {
                        matriz[fila][columna] = "Ocupado";
                    }
                }
            }
        }
        return matriz;
    }
}
