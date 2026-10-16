package Vista;

import Modelo.Estudiante;

public class VistaEstudiante{
    private Estudiante estudiante;

    public VistaEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
    public void mostrarMenuEstudiante(Estudiante estudiante){
        System.out.println("=======SISTEMA DE PRESTAMOS ESTUDIANTILES FMP=======");
        System.out.println("1-Realizar prestamo libro.\n2-Realizar prestamo cabina\n3-Realizar prestamo computadora.");
        System.out.println("Digite ");
    }
}
