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
public class Avion implements Serializable {

    private int id;
    private String tipo;
    private int capacidad;
    private int anio;
    private String marca;
    private int asientosFila;
    private int cantidadFilas;

    public Avion(int id, String tipo, int capacidad, int anio, String marca, int asientos_fila, int cantidad_filas) {
        this.id = id;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.anio = anio;
        this.marca = marca;
        this.asientosFila = asientos_fila;
        this.cantidadFilas = cantidad_filas;
    }

    public Avion() {
        this.id = -1;
        this.tipo = "";
        this.capacidad = 0;
        this.anio = 0;
        this.marca = "";
        this.asientosFila = 0;
        this.cantidadFilas = 0;
    }

    public Avion(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAsientosFila() {
        return asientosFila;
    }

    public void setAsientosFila(int asientosFila) {
        this.asientosFila = asientosFila;
    }

    public int getCantidadFilas() {
        return cantidadFilas;
    }

    public void setCantidadFilas(int cantidadFilas) {
        this.cantidadFilas = cantidadFilas;
    }

    @Override
    public String toString() {
        return "Avion{" + "id=" + id + ", tipo=" + tipo + ", capacidad=" + capacidad + ", anio=" + anio + ", marca=" + marca + ", asientosFila=" + asientosFila + ", cantidadFilas=" + cantidadFilas + '}';
    }

}
