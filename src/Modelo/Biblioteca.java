package Modelo;

import java.util.List;

public class Biblioteca {
    private String sede;
    private String telefono;
    private String correo;
    private List<Estanteria> listaEstanteria;
    private List<Bibliotecario> listaEmpleados;
    private List<Cabina> listaCabinas;
    private List<Computadora> listaComputadoras;
    private List<Estudiante> listaEstudiantes;
    
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

    public List<Bibliotecario> getListaEmpleados() {
        return listaEmpleados;
    }

    public void setListaEmpleados(List<Bibliotecario> listaEmpleados) {
        this.listaEmpleados = listaEmpleados;
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

    public List<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(List<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    
}