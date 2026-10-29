package Modelo;

public class Bibliotecario {
    private String dui;
    private String idTrabajador;
    private String nombreCompleto;
    private String correo;
    private String usuario;
    private String contrasenia;
    private Biblioteca biblioteca;
    
    //
    public Bibliotecario() {
    }

    public Bibliotecario(String dui, String idTrabajador, String nombreCompleto, String correo, String usuario, String contrasenia, Biblioteca biblioteca) {
        this.dui = dui;
        this.idTrabajador = idTrabajador;
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.biblioteca = biblioteca;
    }
    
    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(String idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}