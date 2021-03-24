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
public class Horario implements Serializable {

    private int id;
    private String diaSemana;
    private int horaLlegada;


    public Horario() {
    }

    public Horario(int id, String diaSemana, int horaLlegada) {
        this.id = id;
        this.diaSemana = diaSemana;
        this.horaLlegada = horaLlegada;
    }

    public Horario(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public int getHoraLlegada() {
        return horaLlegada;
    }

    public void setHoraLlegada(int horaLlegada) {
        this.horaLlegada = horaLlegada;
    }

    @Override
    public String toString() {
        return "Horario{" + "id=" + id + ", diaSemana=" + diaSemana + ", horaLlegada=" + horaLlegada + '}';
    }

}
