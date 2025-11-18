package co.uniquindio.poo;

import co.uniquindio.poo.Model.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        UQFit uqfit = new UQFit(1, "Norte", 123456789, "3124567890");

        Administrador admin = new Administrador("Carlos", "1010", 35, "3211111111", 1, "admin", "1234");

        Recepcionista recep = new Recepcionista("Lucía", "2020", 28, "3222222222", 2, JornadaRecepcionista.DIURNA);

        Membresia m1 = new Membresia(new Date(), recep.calcularFechaFin(TipoMembresia.MENSUAL, new Date()), TipoMembresia.MENSUAL, EstadoMembresia.ACTIVA, PlanMembresia.BASICA);
        recep.calcularCostoMembresia(m1);
        Usuario u1 = new Usuario("Andrés Gómez", "1111", 22, "3001111111", m1);
        uqfit.getListPersonas().add(u1);
        Membresia m2 = new Membresia(new Date(), recep.calcularFechaFin(TipoMembresia.TRIMESTRAL, new Date()), TipoMembresia.TRIMESTRAL, EstadoMembresia.ACTIVA, PlanMembresia.PREMIUN);
        recep.calcularCostoMembresia(m2);
        Usuario u2 = new Usuario("Sofía Ríos", "2222", 30, "3002222222", m2);
        uqfit.getListPersonas().add(u2);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -14);
        Date inicioViejo = cal.getTime();
        Calendar cal2 = Calendar.getInstance();
        cal2.add(Calendar.MONTH, -2);
        Date finVencida = cal2.getTime();
        Membresia m3 = new Membresia(inicioViejo, finVencida, TipoMembresia.ANUAL, EstadoMembresia.INACTIVA, PlanMembresia.VIP);
        recep.calcularCostoMembresia(m3);
        Usuario u3 = new Usuario("Diego Torres", "3333", 27, "3003333333", m3);
        uqfit.getListPersonas().add(u3);
        Usuario usuario1 = new Usuario("Juan Perez", "1111", 25, "3001111111");
        Usuario usuario4 = new Usuario("María López", "4444", 40, "3004444444");

        uqfit.getListPersonas().add(usuario1);
        uqfit.getListPersonas().add(usuario4);


        Entrenador entrenador1 = new Entrenador("Pedro", "3030", 30, "3003003000", TipoClase.CROSSFIT);
        Entrenador entrenador2 = new Entrenador("Maria", "4040", 26, "3014040404", TipoClase.YOGA);

        uqfit.getListPersonas().add(entrenador1);
        uqfit.getListPersonas().add(entrenador2);

        Clase clase1 = new Clase("CROSS AM", "6:00 AM", 20, 20, TipoClase.CROSSFIT, entrenador1);
        Clase clase2 = new Clase("YOGA NOCHE", "7:00 PM", 15, 15, TipoClase.YOGA, entrenador2);
        Clase clase3 = new Clase("ZUMBA EXPRESS", "5:00 PM", 18, 18, TipoClase.ZUMBA, entrenador2);

        uqfit.getListClases().add(clase1);
        uqfit.getListClases().add(clase2);
        uqfit.getListClases().add(clase3);



        menuPrincipal(uqfit, admin, recep);
    }


    private static void menuPrincipal(UQFit uqfit, Administrador admin, Recepcionista recep) {
        while (true) {
            System.out.println("""
                    ========================================
                              UQFit - LOGIN
                    ========================================
                    1. Ingresar como Administrador
                    2. Ingresar como Recepcionista
                    3. Salir
                    ========================================
                    """);

            System.out.print("Seleccione opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1 :
                    menuAdministrador(uqfit, admin, recep);
                    break;
                case 2:
                    menuRecepcionista(uqfit, recep);
                    break;
                case 3:
                    System.out.println("¡Gracias por usar UQFit!");
                    return;
                default:
                System.out.println(" Opción incorrecta");
            }
        }
    }

    private static void menuAdministrador(UQFit uqfit, Administrador admin, Recepcionista recep) {
        boolean menu = true;
        while (menu) {
            System.out.println("""
                    ========================================
                       MENÚ ADMINISTRADOR
                    ========================================
                    1. Registrar entrenador
                    2. Modificar entrenador
                    3. Eliminar entrenador
                    4. Asignar entrenador a clase
                    5. Ver entrenadores
                    6. mostrar clases
                    7. mostrar usuarios
                    8. Volver
                    """);
            System.out.print("Seleccione: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            Persona persona;
            String doc;
            switch (opcion) {
                case 1:
                    admin.registrarEntrenador(uqfit);
                    break;
                case 2:
                    admin.imprimirEntrenadores(uqfit);

                    System.out.print("Identificación del entrenador: ");
                    doc = scanner.nextLine();

                    persona = recep.buscarPersonaPorDoc(uqfit, doc);

                    if (persona instanceof Entrenador entrenador) {
                        admin.modificarEntrenador(entrenador);
                    } else {
                        System.out.println("Entrenador no encontrado.");
                    }
                    break;

                case 3:
                    admin.eliminarEntrenador(uqfit);
                    break;
                case 4:
                    admin.imprimirEntrenadores(uqfit);
                    System.out.print("Identificación del entrenador: ");
                    doc = scanner.nextLine();
                    persona = recep.buscarPersonaPorDoc(uqfit, doc);
                    if (persona instanceof Entrenador entrenador) {
                        admin.asignarEntrenadorAClase(uqfit, entrenador);
                    } else {
                        System.out.println("Entrenador no encontrado.");
                    }
                    break;
                case 5:
                    admin.imprimirEntrenadores(uqfit);
                    break;
                case 6:
                    uqfit.mostrarClases();
                case 7:
                    uqfit.mostrarUsuarios();
                    break;
                case 8:
                    menu = false;
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private static void menuRecepcionista(UQFit uqfit, Recepcionista recep) {
        while (true) {
            System.out.println("""
                    ========================================
                           MENÚ RECEPCIONISTA
                    ========================================
                    1. Registrar usuario
                    2. Asignar membresía
                    3. Reservar clase
                    4. Control de acceso
                    5. Generar reporte
                    6. Volver
                    """);
            System.out.print("Seleccione: ");
            int opcion = scanner.nextInt();
            scanner.nextLine();
            Usuario usuario;
            switch (opcion) {
                case 1:
                    recep.registrarUsuario(uqfit);
                    break;
                case 2:

                    usuario = recep.pedirUsuario(uqfit);
                    recep.asignacionMemberesia(usuario);
                    break;
                case 3:
                    usuario = recep.pedirUsuario(uqfit);
                    if (usuario != null) recep.reservarClase(uqfit, usuario);
                    break;
                case 4:
                    usuario = recep.pedirUsuario(uqfit);
                    if (usuario != null) recep.controlAcceso(uqfit, usuario);
                    break;
                case 5:
                    recep.generarReporte(uqfit);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Opción incorrecta");
            }
        }
    }
}

