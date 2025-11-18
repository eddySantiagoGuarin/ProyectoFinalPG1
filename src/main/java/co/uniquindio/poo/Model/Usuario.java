package co.uniquindio.poo.Model;

public class Usuario  extends Persona {

    Membresia membresia ;

    public Usuario(String nombre, String identificacion, int edad, String telefono) {
        super(nombre, identificacion, edad, telefono);
    }

    public Usuario(String nombre, String identificacion, int edad, String telefono, Membresia membresia) {
        super(nombre, identificacion, edad, telefono);
        this.membresia=membresia;
    }

    public Membresia getMembresia() {
        return membresia;
    }

    public void setMembresia(Membresia membresia) {
        this.membresia = membresia;
    }

    @Override
    public String toString() {
        return  "==============================\n" +
                "        USUARIO\n" +
                "==============================\n" +
                "Nombre: " + getNombre() + "\n" +
                "Identificación: " + getIdentificacion() + "\n" +
                "Edad: " + getEdad() + "\n" +
                "Teléfono: " + getTelefono() + "\n" +
                (membresia != null ?
                        "Membresía: " + membresia.getTipoMembresia() + " (" + membresia.getEstadoMembresia() +" (" + membresia.getPlanMembresia() + ")\n" +
                                "Vence: " + membresia.getFechaVencimiento() + "\n"
                        :
                        "Membresía: Sin asignar\n"
                );
    }

}
