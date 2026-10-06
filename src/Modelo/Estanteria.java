package Modelo;

public class Estanteria {
    private String idEstanteria;
    private String catalogo;
    private int capacidad;
    
    public void añadirLibro(){}
    public void retirarLibro(){}
    
    //
    public Estanteria() {
    }

    public String getIdEstanteria() {
        return idEstanteria;
    }

    public void setIdEstanteria(String idEstanteria) {
        this.idEstanteria = idEstanteria;
    }

    public String getCatalogo() {
        return catalogo;
    }

    public void setCatalogo(String catalogo) {
        this.catalogo = catalogo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }    
}
