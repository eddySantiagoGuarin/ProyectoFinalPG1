package co.uniquindio.poo.Model;

import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RecepcionistaTest {

    @Test
    public void testCalcularCostoMembresiaTrimestralVIP() {
        Recepcionista recep = new Recepcionista("eddy","1010",19,"300",1,JornadaRecepcionista.DIURNA);
        Membresia membresia = new Membresia(
                new Date(),
                new Date(),
                TipoMembresia.TRIMESTRAL,
                EstadoMembresia.ACTIVA,
                PlanMembresia.VIP
        );
        recep.calcularCostoMembresia(membresia);
        assertEquals(195000, membresia.getCosto());
    }

    @Test
    public void testCalcularFechaFinMensual() {
        Recepcionista recep = new Recepcionista("eddy","1010",19,"300",1,JornadaRecepcionista.DIURNA);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.SEPTEMBER, 1);
        Date inicio = calendar.getTime();
        Date fin = recep.calcularFechaFin(TipoMembresia.MENSUAL, inicio);
        calendar.add(Calendar.DATE, 30);
        Date esperado = calendar.getTime();
        assertEquals(esperado, fin);
    }

    @Test
    public void testVerificarCuposDisponibles() {
        Recepcionista recep = new Recepcionista("eddy","1010",19,"300",1,JornadaRecepcionista.DIURNA);
        Clase clase = new Clase("Yoga","6am",10,0,TipoClase.YOGA,null);
        boolean resultado = recep.verificarCupos(clase);
        assertTrue(resultado);
    }

    @Test
    public void testEvaluarEstadoMembresiaVencida() {
        Recepcionista recep = new Recepcionista("eddy","1010",19,"300",1,JornadaRecepcionista.DIURNA);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);
        Date vencida = calendar.getTime();
        Membresia membresia = new Membresia(new Date(), vencida, TipoMembresia.MENSUAL, EstadoMembresia.ACTIVA, PlanMembresia.BASICA);
        Usuario usuario = new Usuario("santiago","123",19,"300", membresia);
        recep.evaluarEstadoMembresia(usuario);
        assertEquals(EstadoMembresia.INACTIVA, usuario.getMembresia().getEstadoMembresia());
    }


}