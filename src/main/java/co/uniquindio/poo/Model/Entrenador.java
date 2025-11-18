package co.uniquindio.poo.Model;

public class Entrenador extends Trabajador{
    private TipoClase especialidad;

    public Entrenador(String nombre, String identificacion, int edad, String telefono, TipoClase especialidad) {
        super(nombre, identificacion, edad, telefono);
        this.especialidad=especialidad;
    }

    public Entrenador(String nombre, String identificacion, int edad, String telefono, int codigoTrabajador, TipoClase especialidad) {
        super(nombre, identificacion, edad, telefono, codigoTrabajador);
        this.especialidad=especialidad;
    }

    public TipoClase getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(TipoClase especialidad) {
        this.especialidad = especialidad;
    }
}
