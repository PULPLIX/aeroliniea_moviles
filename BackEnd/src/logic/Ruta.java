/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;
import java.util.Collection;


/**
 *
 * @author david
 */
public class Ruta implements Serializable {

    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private int id;
    private double precio;
    private double porcentajeDescuento;

    private Ciudad ciudadOrigen;
    private Ciudad ciudadDestino;
    private Horario horarioId;

    public Ruta() {
        this.id = -1;
        this.precio = 0.0;
        this.porcentajeDescuento = 0.0;
        this.ciudadOrigen = new Ciudad();
        this.ciudadDestino = new Ciudad();
        this.horarioId = new Horario();
    }

    public Ruta(int id, double precio, double porcentajeDescuento, Ciudad ciudadOrigen, Ciudad ciudadDestino, Horario horarioId) {
        this.id = id;
        this.precio = precio;
        this.porcentajeDescuento = porcentajeDescuento;
        this.ciudadOrigen = ciudadOrigen;
        this.ciudadDestino = ciudadDestino;
        this.horarioId = horarioId;
    }

    public Ruta(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(double porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }



    public Ciudad getCiudadOrigen() {
        return ciudadOrigen;
    }

    public void setCiudadOrigen(Ciudad ciudadOrigen) {
        this.ciudadOrigen = ciudadOrigen;
    }

    public Ciudad getCiudadDestino() {
        return ciudadDestino;
    }

    public void setCiudadDestino(Ciudad ciudadDestino) {
        this.ciudadDestino = ciudadDestino;
    }

    public Horario getHorarioId() {
        return horarioId;
    }

    public void setHorarioId(Horario horarioId) {
        this.horarioId = horarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruta)) {
            return false;
        }
        Ruta other = (Ruta) object;

        return true;
    }

    @Override
    public String toString() {
        return "logic.Rutas[ id=" + id + " ]";
    }
    
}
