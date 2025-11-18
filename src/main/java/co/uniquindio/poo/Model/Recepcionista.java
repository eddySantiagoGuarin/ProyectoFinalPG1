package co.uniquindio.poo.Model;

import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Recepcionista extends Trabajador implements PreguntarClase{
    private JornadaRecepcionista jornadaRecepcionista;

    public Recepcionista(String nombre, String identificacion, int edad, String telefono, int codigoTrabajador,JornadaRecepcionista jornadaRecepcionista) {
        super(nombre, identificacion, edad, telefono, codigoTrabajador);
        this.jornadaRecepcionista=jornadaRecepcionista;
    }

    public JornadaRecepcionista getJornadaRecepcionista() {
        return jornadaRecepcionista;
    }

    public void setJornadaRecepcionista(JornadaRecepcionista jornadaRecepcionista) {
        this.jornadaRecepcionista = jornadaRecepcionista;
    }

    Scanner scanner = new Scanner(System.in);

    public void registrarUsuario (UQFit uqfit){


        System.out.println("Ingrese el nombre del Usuario");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el documento del Usuario");
        String documento = scanner.nextLine();

        System.out.println("Ingrese la edad del Usuario");
        int edad = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el telefono del Usuario");
        String telefono = scanner.nextLine();

        Usuario usuario =new Usuario (nombre,documento,edad,telefono);

        uqfit.getListPersonas().add(usuario);
        System.out.println("Usuario Registrado exitosamente");


    }

    public  void calcularCostoMembresia(Membresia membresia){
        double costo =membresia.getCosto();
        if(membresia.getTipoMembresia()==TipoMembresia.TRIMESTRAL){
            costo*=3;
        }else if(membresia.getTipoMembresia()==TipoMembresia.ANUAL) {
            costo*=12;
        }

        if(membresia.getPlanMembresia()==PlanMembresia.VIP){
            costo*=1.3;
        }else if (membresia.getPlanMembresia()==PlanMembresia.PREMIUN){
            costo*=1.8;
        }
        membresia.setCosto((int)costo);
    }

    public TipoMembresia preguntarTipoMembresia(){
        System.out.println("Ingrese el tipo de Membresia por favor: MENSUAL,TRIMESTRAL,ANUAL   ");

        String opcion= scanner.nextLine().toUpperCase();

        try{
            TipoMembresia tipoMembresia= TipoMembresia.valueOf(opcion);
            return  tipoMembresia;
        } catch (IllegalArgumentException e) {
            System.out.println("¡Error! Ingrese un tipo de Membresia correcto");
            return preguntarTipoMembresia();
        }

    }

    public PlanMembresia preguntarPlanMenbresia(){
        System.out.println("Ingrese el tipo de plan para la Membresia: BASIC, PREMIUN, VIP");
        String opcion = scanner.nextLine().toUpperCase();

        try{
            PlanMembresia planMembresia= PlanMembresia.valueOf(opcion);
            return  planMembresia;
        } catch (IllegalArgumentException e) {
            System.out.println("¡Error! Ingrese un tipo de Plan correcto");
            return preguntarPlanMenbresia();
        }
    }


    public EstadoMembresia preguntarEstadoMembresia(){
        System.out.println("Ingrese el estado de la Membresia: ACTIVA, INACTIVA  ");
        String opcion = scanner.nextLine().toUpperCase();

        try{
            EstadoMembresia estadoMembresia= EstadoMembresia.valueOf(opcion);
            return  estadoMembresia;
        } catch (IllegalArgumentException e) {
            System.out.println("¡Error! Ingrese un Estado correcto para la Membresia");
            return preguntarEstadoMembresia();
        }
    }

    public Date calcularFechaFin(TipoMembresia tipoMembresia,Date date){
        int diasASumar = 0;
        if(tipoMembresia== TipoMembresia.ANUAL){
            diasASumar=365;
        }else if(tipoMembresia==TipoMembresia.TRIMESTRAL){
            diasASumar=90;
        }else {
            diasASumar=30;
        }
        Calendar calendar =Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE,diasASumar);

        return calendar.getTime();
    }

    public Membresia crearMembresia(){
        PlanMembresia planMembresia= preguntarPlanMenbresia();
        TipoMembresia tipoMembresia= preguntarTipoMembresia();
        EstadoMembresia estadoMembresia=preguntarEstadoMembresia();
        Date fechaInicio=new Date();
        Date fecahFin = calcularFechaFin(tipoMembresia,fechaInicio);
        Membresia membresia = new Membresia(fechaInicio,fecahFin,tipoMembresia,estadoMembresia,planMembresia);
        calcularCostoMembresia(membresia);
        return membresia;
    }

    public void asignacionMemberesia(Usuario usuario){
        Membresia membresia = crearMembresia();
        usuario.setMembresia(membresia);
    }

    @Override
    public Clase preguntarClase(UQFit uqfit) {
        System.out.print("Ingrese el nombre de la clase que el usuario desea reservar: ");
        String nombreClase = scanner.nextLine();
        for (Clase clase : uqfit.getListClases()) {
            if (clase.getNombre().equalsIgnoreCase(nombreClase)) {
                return clase;
            }
        }
        System.out.println("¡Error! El nombre de la clse no coincide con una clase dispoible");
        return preguntarClase(uqfit);
    }


    public boolean verificarCupos(Clase clase){
        boolean sePuedeReservar=false;
        if (clase.getCupo()==0){
            System.out.println("Cupos no disponibles ");
        }else {
            clase.setCupo(clase.getCupo()-1);
            sePuedeReservar=true;
        }
        return  sePuedeReservar;
    }

    public void reservarClase(UQFit uqfit,Usuario usuario){
        uqfit.mostrarClases();
        Clase clase = preguntarClase(uqfit);
        boolean sePuedeReservar = verificarCupos(clase);
        if(sePuedeReservar){
            clase.getLisUsuariosClase().add(usuario);
        }

    }

    public boolean verificarExistenciaUsuario(UQFit uqfit,Usuario usuario){
        boolean existe=false;
        for (Persona usuarioExistente:uqfit.getListPersonas()){
            if (usuarioExistente.getIdentificacion().equals(usuario.getIdentificacion())){
                existe=true;
            }
        }
        return existe;
    }

    public void evaluarEstadoMembresia(Usuario usuario){
        Date fechaActual=new Date();
        if(fechaActual.after(usuario.getMembresia().getFechaVencimiento())){
            usuario.getMembresia().setEstadoMembresia(EstadoMembresia.INACTIVA);
        }
    }
    @Override
    public void controlAcceso(UQFit uqfit, Persona usuario) {

        boolean existe = verificarExistenciaUsuario(uqfit, ((Usuario)usuario));

        if (!existe) {
            System.out.println("Usuario no existe, Desea registrarse?");
            return;
        }
        if (((Usuario) usuario).getMembresia() == null) {
            System.out.println("¡Sin membresia, adquiera una!");
            return;
        }
        evaluarEstadoMembresia(((Usuario) usuario));
        if (((Usuario) usuario).getMembresia().getEstadoMembresia() == EstadoMembresia.ACTIVA) {
            System.out.println("¡Ingrese señor Usuario!");
        } else if (((Usuario) usuario).getMembresia().getEstadoMembresia() == EstadoMembresia.INACTIVA) {
            System.out.println("¡Actualice su membresia por favor!");
        }
    }

    public Persona buscarPersonaPorDoc(UQFit uqfit, String identificacion) {
        for (Persona persona : uqfit.getListPersonas()) {
            if (persona.getIdentificacion().equals(identificacion)) {
                return persona;
            }
        }
        return null;
    }

    public Usuario pedirUsuario(UQFit uqfit) {
        System.out.print("Ingrese la identificacion del Usuario: ");
        String identificacion = scanner.nextLine();

        Persona personaEncontrada = buscarPersonaPorDoc(uqfit, identificacion);

        if (personaEncontrada instanceof Usuario usuario) {
            return usuario;
        } else {
            System.out.println("¡Error! Usuario no encontrado.");
            return null;
        }
    }



    public void imprimirUsuariosActivos(UQFit uqfit){
        for (Persona persona : uqfit.getListPersonas()){
            if(persona instanceof Usuario){
                Usuario usuario = (Usuario) persona;
                if(usuario.getMembresia() != null && usuario.getMembresia().getEstadoMembresia() == EstadoMembresia.ACTIVA){
                    System.out.println(usuario);
                }
            }
        }
    }


    public void imprimirUsuariosVIP(UQFit uqfit){
        for (Persona persona : uqfit.getListPersonas()){
            if(persona instanceof Usuario){
                Usuario usuario = (Usuario) persona;
                if(usuario.getMembresia() != null && usuario.getMembresia().getPlanMembresia()==PlanMembresia.VIP){
                    System.out.println(usuario);
                }
            }
        }
    }

    public void generarReporte(UQFit uqfit){
        boolean salir = true;
        while (salir){
            System.out.println("Ingrese la opcion que desea hacer: 1.Imprimir Usuarios Activos," +
                    " 2. Imprimir Usuarios VIP," +
                    " 3.salir");
            int opcion= scanner.nextInt();
            switch (opcion){
                case 1 :
                    imprimirUsuariosActivos(uqfit);
                    break;
                case 2 :
                    imprimirUsuariosVIP(uqfit);
                    break;
                case 3 :
                    salir = false;
                    break;
                default:
                    System.out.println("¡Error! opcion incorrecta");
            }

        }

    }

}
