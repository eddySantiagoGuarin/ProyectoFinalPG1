package co.uniquindio.poo.Model;


import java.util.ArrayList;
import java.util.List;

public class Clase {

    private  String nombre;
    private String horario;
    private int cupoMax;
    private int cupo;
    private TipoClase tipoClase;

    //Relaciones de agregacion
    private List<Usuario> lisUsuariosClase;

    //Relaciones de Asociacion
    private Entrenador theEntrenador;

    public Clase(String nombre, String horario, int cupoMax, int cupo, TipoClase tipoClase, Entrenador theEntrenador) {
        this.nombre = nombre;
        this.horario = horario;
        this.cupoMax = cupoMax;
        this.cupo = cupo;
        assert cupo>=0:"Los cupos de la clase no pueden ser negativos por logica";
        this.tipoClase = tipoClase;
        this.lisUsuariosClase =  new ArrayList<>();;
        this.theEntrenador = theEntrenador;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getCupoMax() {
        return cupoMax;
    }

    public void setCupoMax(int cupoMax) {
        this.cupoMax = cupoMax;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public TipoClase getTipoClase() {
        return tipoClase;
    }

    public void setTipoClase(TipoClase tipoClase) {
        this.tipoClase = tipoClase;
    }

    public List<Usuario> getLisUsuariosClase() {
        return lisUsuariosClase;
    }

    public void setLisUsuariosClase(List<Usuario> lisUsuariosClase) {
        this.lisUsuariosClase = lisUsuariosClase;
    }

    public Entrenador getTheEntrenador() {
        return theEntrenador;
    }

    public void setTheEntrenador(Entrenador theEntrenador) {
        this.theEntrenador = theEntrenador;
    }

    @Override
    public String toString() {
        return "-----------------------------------------\n" +
                " CLASE PROGRAMADA\n" +
                "-----------------------------------------\n" +
                " Nombre: " + nombre + "\n" +
                " Horario: " + horario + "\n" +
                " Instructor: " + theEntrenador.getNombre() + "\n" +
                " Cupos disponibles: " + cupo + "\n" +
                "-----------------------------------------";
    }



}


