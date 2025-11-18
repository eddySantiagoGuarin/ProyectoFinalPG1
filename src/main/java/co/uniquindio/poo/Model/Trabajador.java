package co.uniquindio.poo.Model;

public class  Trabajador extends Persona {
    private int codigoTrabajador;

    public Trabajador(String nombre, String identificacion, int edad, String telefono) {
        super(nombre, identificacion, edad, telefono);
    }

    public Trabajador(String nombre, String identificacion, int edad, String telefono, int codigoTrabajador) {
        super(nombre, identificacion, edad, telefono);
        this.codigoTrabajador=codigoTrabajador;
    }

    public int getCodigoTrabajador() {
        return codigoTrabajador;
    }

    public void setCodigoTrabajador(int codigoTrabajador) {
        this.codigoTrabajador = codigoTrabajador;
    }


}
