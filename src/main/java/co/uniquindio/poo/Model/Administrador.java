package co.uniquindio.poo.Model;

import java.util.Iterator;
import java.util.Scanner;

public class Administrador extends Trabajador implements PreguntarClase{
    private String usuario;
    private String contrasena;

    public Administrador(String nombre, String identificacion, int edad, String telefono, int codigoTrabajador,String usuario, String contrasena) {
        super(nombre, identificacion, edad, telefono, codigoTrabajador);
        this.usuario=usuario;
        this.contrasena=contrasena;
    }

    public String getUsuariouario() {
        return usuario;
    }

    public void setUsuarioSuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    Scanner scanner = new Scanner(System.in);

    public TipoClase preguntarEspecialidad(){
        System.out.println("Inrese la especialidad del entrenador:YOGA,\n" + "    SPINNING,\n" + "    ZUMBA,\n" + "    CROSSFIT,\n" + "    PILATES,\n" + "    FUNCIONAL,\n" + "    BOXEO,");
        String opcion = scanner.nextLine().toUpperCase();
        try {
            TipoClase especialidad = TipoClase.valueOf(opcion);
            return especialidad;
        } catch (IllegalArgumentException e) {
            System.out.println("!Error¡Ingrese una especialidad disponible");
            return preguntarEspecialidad();
        }
    }

    public void registrarEntrenador (UQFit uqfit){


        System.out.println("Ingrese el nombre del Entrenador");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el documento del Entrenador");
        String documento = scanner.nextLine();

        System.out.println("Ingrese la edad del Entrenador");
        int edad = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el telefono del Entrenador");
        String telefono = scanner.nextLine();

        System.out.println("Ingrese la especialidad del Entrenador");
        TipoClase especialidad=preguntarEspecialidad();

        Entrenador entrenador =new Entrenador(nombre,documento,edad,telefono,especialidad);

        uqfit.getListPersonas().add(entrenador);


    }


    public void modificarEntrenador(Entrenador entrenador){

        boolean salir= true;

        while (salir){

            System.out.print("Que dato desea actualizar: 1.Nombre, 2.Documento, 3.Edad, 4.Telefono, 5.Especialidad, 6,Salir");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion){
                case 1:
                    System.out.println("Ingrese el nombre del Entrenador");
                    String nombre = scanner.nextLine();
                    entrenador.setNombre(nombre);
                    System.out.println("Dato actualizado");
                    break;
                case 2:
                    System.out.println("Ingrese el documento del Entrenador");
                    String identificacion = scanner.nextLine();
                    entrenador.setIdentificacion(identificacion);
                    System.out.println("Dato actualizado");
                    break;
                case 3:
                    System.out.println("Ingrese la edad del Entrenador");
                    int edad = scanner.nextInt();
                    entrenador.setEdad(edad);
                    System.out.println("Dato actualizado");
                    break;
                case 4:
                    System.out.println("Ingrese el telefono del Entrenador");
                    String telefono = scanner.nextLine();
                    entrenador.setTelefono(telefono);
                    System.out.println("Dato actualizado");
                    break;
                case 5:
                    System.out.println("Ingrese la especialidad del Entrenador");
                    TipoClase especialidad=preguntarEspecialidad();
                    entrenador.setEspecialidad(especialidad);
                    System.out.println("Dato actualizado");
                    break;
                case 6:
                    salir=false;
                    break;
                default:
                    System.out.println("¡Error¡ Ingrese una opcion valida");
            }
        }
    }

    public void imprimirEntrenadores(UQFit uqfit){
        for (Persona entrenador: uqfit.getListPersonas()){
            if(entrenador instanceof Entrenador){
                System.out.println(entrenador);
            }
        }

    }

    public void eliminarEntrenador(UQFit uqfit){
        imprimirEntrenadores(uqfit);
        System.out.print("Ingrese el documento del usuario que desea eliminar: ");
        String documento =scanner.nextLine();
        Iterator<Persona> personaiterdora = uqfit.getListPersonas().iterator();
        while(personaiterdora.hasNext()){
            Persona persona = personaiterdora.next();
            if(persona instanceof Entrenador && ((Entrenador) persona).getIdentificacion().equals(documento)){
                personaiterdora.remove();
                System.out.println("Entrenador eliminado correctamente.");
                break;
            }
        }
    }

    public Clase preguntarClase(UQFit uqfit) {
        System.out.print("Ingrese el nombre de la clase que va a asignar al Entrenador: ");
        String nombreClase = scanner.nextLine();
        for (Clase clase : uqfit.getListClases()) {
            if (clase.getNombre().equalsIgnoreCase(nombreClase)) {
                return clase;
            }
        }
        System.out.println("¡Error! El nombre de la clse no coincidecon una clase disponible");
        return preguntarClase(uqfit);
    }

    public void asignarEntrenadorAClase(UQFit uqfit,Entrenador entrenador){
        uqfit.mostrarClases();
        Clase clase= preguntarClase(uqfit);
        clase.setTheEntrenador(entrenador);

    }

    @Override
    public void controlAcceso(UQFit uqfit, Persona persona) {
        boolean encontrado = false;
        for(Persona personaRegistrada : uqfit.getListPersonas()){
            if(personaRegistrada.getIdentificacion().equals(persona.getIdentificacion())){
                System.out.println("ACCESO PERMITIDO");
                encontrado = true;
                break;
            }
        }
        if(!encontrado){
            System.out.println("ACCESO DENEGADO");
        }
    }


}
