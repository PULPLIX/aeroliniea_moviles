a/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author david
 */

public class Vuelo implements Serializable {
    private int id;
    private String modalidad;
    private int duracion;
    private Date fecha;
    private Avion avionId;
    private Ruta rutaId;
    private Collection<Tiquete> tiquetesCollection;

    public Vuelo() {
    }
    
    public Vuelo(int id, String modalidad, int duracion, Date fecha, Avion avionId, Ruta rutaId, Collection<Tiquete> tiquetesCollection) {
        this.id = id;
        this.modalidad = modalidad;
        this.duracion = duracion;
        this.fecha = fecha;
        this.avionId = avionId;
        this.rutaId = rutaId;
        this.tiquetesCollection = tiquetesCollection;
    }

    public Vuelo(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Avion getAvionId() {
        return avionId;
    }

    public void setAvionId(Avion avionId) {
        this.avionId = avionId;
    }

    public Ruta getRutaId() {
        return rutaId;
    }

    public void setRutaId(Ruta rutaId) {
        this.rutaId = rutaId;
    }

    public Collection<Tiquete> getTiquetesCollection() {
        return tiquetesCollection;
    }

    public void setTiquetesCollection(Collection<Tiquete> tiquetesCollection) {
        this.tiquetesCollection = tiquetesCollection;
    }
    
}
