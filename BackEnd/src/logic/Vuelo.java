/* 
* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 *
 * @author david
 */
public class Vuelo implements Serializable {

    private int id;
    private int modalidad;
    private int duracion;
    private Date fecha;
    private Avion avionId;
    private Ruta rutaId;
    private Collection<Tiquete> tiquetesCollection;

    public Vuelo() {
        this.id = -1;
        this.modalidad = 0;
        this.duracion = 0;
        this.fecha = new Date();
        this.avionId = new Avion();
        this.rutaId = new Ruta();
        this.tiquetesCollection = new ArrayList<>();
    }

    public Vuelo(int id, int modalidad, int duracion, String fecha, Avion avionId, Ruta rutaId) {
        this.id = id;
        this.modalidad = modalidad;
        this.duracion = duracion;
        this.fecha = this.parseDate(fecha);
        this.avionId = avionId;
        this.rutaId = rutaId;
        this.tiquetesCollection = new ArrayList<>();
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

    public int getModalidad() {
        return modalidad;
    }

    public void setModalidad(int modalidad) {
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

    public void setFecha(String fecha) {
        this.fecha = this.parseDate(fecha);
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

    public Date parseDate(String dateStr) {
        LocalDate fecha = LocalDate.parse(dateStr);
        Date date = java.util.Date.from(fecha.atStartOfDay()
                .atZone(ZoneId.systemDefault())
                .toInstant());
        return date;
    }

    public Collection<Tiquete> getTiquetesCollection() {
        return tiquetesCollection;
    }

    public void setTiquetesCollection(Collection<Tiquete> tiquetesCollection) {
        this.tiquetesCollection = tiquetesCollection;
    }

}
