package Modelo;

import Modelo.PrestamoLibro;
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
    
    public String [][] matrizHorarioCabina(Cabina cabina){
        String[][] matriz = new String[8][5];
        
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                matriz[i][j] = "Libre";
            }
        }
        
        for(ReservaCabina reserva : mapaReservaCabina.values()){
            if(reserva.isActivo() && reserva.getCabina().getIdCabina().equalsIgnoreCase(cabina.getIdCabina())){
                
                int horaInicio = reserva.getHoraInicio().getHour();
                int fila = horaInicio-8;
                
                java.time.DayOfWeek diaSemana = reserva.getFecha().getDayOfWeek();
                int columna = diaSemana.getValue() - 1;
                
                if(fila >= 0 && fila<8 && columna >=0 && columna < 5){
                    matriz[fila][columna] = "Ocupado/Reservada";
                }
            }
        }
        return matriz;
    }
}
