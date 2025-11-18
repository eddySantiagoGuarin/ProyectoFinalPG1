package co.uniquindio.poo.Model;

import java.util.Date;

public class Membresia {
    private int costo=50000;
    private Date fechaInicio;
    private  Date fechaVencimiento;
    private TipoMembresia tipoMembresia ;
    private EstadoMembresia estadoMembresia;
    private PlanMembresia planMembresia;

    public Membresia( Date fechaInicio, Date fechaVencimiento, TipoMembresia tipoMembresia, EstadoMembresia estadoMembresia, PlanMembresia planMembresia) {
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.tipoMembresia = tipoMembresia;
        this.estadoMembresia = estadoMembresia;
        this.planMembresia = planMembresia;
    }


    public Membresia(int costo, Date fechaInicio, Date fechaVencimiento, TipoMembresia tipoMembresia, EstadoMembresia estadoMembresia, PlanMembresia planMembresia) {
        this.costo = costo;
        this.fechaInicio = fechaInicio;
        this.fechaVencimiento = fechaVencimiento;
        this.tipoMembresia = tipoMembresia;
        this.estadoMembresia = estadoMembresia;
        this.planMembresia = planMembresia;
    }




    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(Date fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public TipoMembresia getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(TipoMembresia tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }

    public EstadoMembresia getEstadoMembresia() {
        return estadoMembresia;
    }

    public void setEstadoMembresia(EstadoMembresia estadoMembresia) {
        this.estadoMembresia = estadoMembresia;
    }

    public PlanMembresia getPlanMembresia() {
        return planMembresia;
    }

    public void setPlanMembresia(PlanMembresia planMembresia) {
        this.planMembresia = planMembresia;
    }
}
