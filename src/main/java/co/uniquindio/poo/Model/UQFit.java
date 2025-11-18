package co.uniquindio.poo.Model;

import java.util.ArrayList;
import java.util.List;

public class UQFit {

    private int nombre;
    private String direccion;
    private int nit;
    private String telefono;

    // Relaciones de Composion
    private List<Membresia> lisMembresias ;
    private List<Persona> listPersonas ;
    private List<Clase> listClases ;

    public UQFit(int nombre, String direccion, int nit, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.nit = nit;
        this.telefono = telefono;
        this.lisMembresias =  new ArrayList<>();
        this.listPersonas =  new ArrayList<>();
        this.listClases =  new ArrayList<>();
    }

    

    public int getNombre() {
        return nombre;
    }

    public void setNombre(int nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Membresia> getLisMembresias() {
        return lisMembresias;
    }

    public void setLisMembresias(List<Membresia> lisMembresias) {
        this.lisMembresias = lisMembresias;
    }

    public  List<Persona> getListPersonas() {
        return listPersonas;
    }

    public void setListPersonas(List<Persona> listPersonas) {
        this.listPersonas = listPersonas;
    }

    public  List<Clase> getListClases() {
        return listClases;
    }

    public void setListClases(List<Clase> listClases) {
        this.listClases = listClases;
    }

    public void mostrarClases() {
        for (Clase clase : getListClases()) {
            System.out.println(clase);
        }
    }

    public void mostrarUsuarios() {
        for (Persona persona : getListPersonas()) {
            if (persona instanceof Usuario){
                System.out.println(persona);
            }

        }
    }



}
