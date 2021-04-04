/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DataAccess.ServicioTiquete;
import DataAccess.ServicioVuelo;
import Exceptions.DbException;
import Exceptions.GeneralException;
import java.util.Collection;
import logic.Tiquete;
import logic.Vuelo;

/**
 *
 * @author david
 */
public class ModelTiquete {

    private final ServicioTiquete tiqueteDao;
    public static ModelTiquete ModelInstance = null;

    public ModelTiquete() throws GeneralException {
        tiqueteDao = ServicioTiquete.getSingletonInstance();
    }

    public static ModelTiquete getInstance() throws GeneralException {
        if (ModelInstance == null) {
            ModelInstance = new ModelTiquete();
        }
        return ModelInstance;
    }

    public Tiquete getTiquete(int id) throws GeneralException, DbException {
        return tiqueteDao.getTiquete(id);
    }

    public Collection listarTiquete() throws GeneralException, DbException {
        return tiqueteDao.listar_tiquete();
    }

    public void agrergar(Tiquete nuevoTiquete) throws Exception {
        tiqueteDao.insercionTiquete(nuevoTiquete);
    }

    public void actualizar(Tiquete nuevoTiquete) throws Exception {
        tiqueteDao.updateTiquete(nuevoTiquete);
    }

    public void eliminar(int id) throws Exception {
        tiqueteDao.deleteTiquete(id);
    }
}

