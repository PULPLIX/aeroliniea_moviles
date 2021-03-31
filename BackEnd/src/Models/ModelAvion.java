/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DataAccess.ServicioAviones;
import Exceptions.DbException;
import Exceptions.GeneralException;
import java.io.Serializable;
import java.util.Collection;
import logic.Avion;

/**
 *
 * @author david
 */
public class ModelAvion implements Serializable {

    private final ServicioAviones avionDao;
    public static ModelAvion ModelInstance = null;

    public ModelAvion() throws GeneralException {
        avionDao = ServicioAviones.getSingletonInstance();
    }

    public static ModelAvion getInstance() throws GeneralException {
        if (ModelInstance == null) {
            ModelInstance = new ModelAvion();
        }
        return ModelInstance;
    }

    public Avion getAvion(int id) throws GeneralException, DbException {
        return avionDao.getAvion(id);
    }

    public Collection listarAviones() throws GeneralException, DbException {
        return avionDao.listar_aviones();
    }

    public void agrergar(Avion nuevoAvion) throws Exception {
        avionDao.insercionAviones(nuevoAvion);
    }

}
