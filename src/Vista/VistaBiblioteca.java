package Vista;

import Modelo.Biblioteca;

public class VistaBiblioteca {
    private Biblioteca biblioteca;

    public VistaBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
    
    
    
    public void mostrarDetallesBiblioteca(Biblioteca biblioteca){
        System.out.println("=====INFORMACION GENERAL=====");
        System.out.println("BIBLIOTECA: "+biblioteca.getSede());
        System.out.println("CORREO: "+biblioteca.getCorreo());
        System.out.println("TELEFONO: "+biblioteca.getTelefono());
        System.out.println("=============================");
    }
    
}
