package Modelo;

import java.util.List;

public class Libro {
    private String isbn;
    private String titulo;
    private String autor;
    private int numPaginas;
    private String codigo;
    private String estado;
    private Estanteria estanteria;
    private boolean disponible;
    private List<PrestamoLibro> listaPrestamosLibro;
    
    public Libro(String isbn, String titulo, String autor, int numPaginas, String codigo){
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.numPaginas = numPaginas;
        this.codigo = codigo;
        this.disponible = true;
        this.estado = "En estanteria";
    }
    
    public void actualizarDatos(){}
    //

    public Libro() {
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getNumPaginas() {
        return numPaginas;
    }

    public void setNumPaginas(int numPaginas) {
        this.numPaginas = numPaginas;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Estanteria getEstanteria() {
        return estanteria;
    }

    public void setEstanteria(Estanteria estanteria) {
        this.estanteria = estanteria;
    }

    public List<PrestamoLibro> getListaPrestamosLibro() {
        return listaPrestamosLibro;
    }

    public void setListaPrestamosLibro(List<PrestamoLibro> listaPrestamosLibro) {
        this.listaPrestamosLibro = listaPrestamosLibro;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
}