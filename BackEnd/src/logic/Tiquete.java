/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.io.Serializable;

/**
 *
 * @author david
 */

public class Tiquete implements Serializable {

    private int id;
    private Usuario usuarioId;
    private Vuelo vueloId;
    private String precioFinal;
    private int filaAsisento;
    private int columnaAsiento;
    private String formaPago;

    public Tiquete() {
    }

    public Tiquete( int id,Usuario usuarioId, Vuelo vueloId, String precioFinal, int filaAsisento, int columnaAsiento, String formaPago) {
        this.id = id;
        this.precioFinal = precioFinal;
        this.filaAsisento = filaAsisento;
        this.columnaAsiento = columnaAsiento;
        this.formaPago = formaPago;
        this.usuarioId = usuarioId;
        this.vueloId = vueloId;
    }

    public Tiquete(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(String precioFinal) {
        this.precioFinal = precioFinal;
    }

    public int getFilaAsisento() {
        return filaAsisento;
    }

    public void setFilaAsisento(int filaAsisento) {
        this.filaAsisento = filaAsisento;
    }

    public int getColumnaAsiento() {
        return columnaAsiento;
    }

    public void setColumnaAsiento(int columnaAsiento) {
        this.columnaAsiento = columnaAsiento;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Vuelo getVueloId() {
        return vueloId;
    }

    public void setVueloId(Vuelo vueloId) {
        this.vueloId = vueloId;
    }

    @Override
    public String toString() {
        return "Tiquete{" + "id=" + id + ", usuarioId=" + usuarioId.toString() + ", vueloId=" + vueloId.toString() + ", precioFinal=" + precioFinal + ", filaAsisento=" + filaAsisento + ", columnaAsiento=" + columnaAsiento + ", formaPago=" + formaPago + '}';
    }


    
    
}
