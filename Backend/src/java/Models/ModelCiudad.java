/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DataAccess.ServicioCiudad;
import Exceptions.DbException;
import Exceptions.GeneralException;
import java.io.Serializable;
import java.util.Collection;
import logic.Ciudad;

/**
 *
 * @author david
 */
public class ModelCiudad implements Serializable {

    private final ServicioCiudad cudadDao;
    public static ModelCiudad ModelInstance = null;

    public ModelCiudad() throws GeneralException {
        cudadDao = ServicioCiudad.getSingletonInstance();
    }

    public static ModelCiudad getInstance() throws GeneralException {
        if (ModelInstance == null) {
            ModelInstance = new ModelCiudad();
        }
        return ModelInstance;
    }

    public Ciudad getCiudad(int id) throws GeneralException, DbException {
        return cudadDao.getCiudad(id);
    }

    public Collection listarCiudades() throws GeneralException, DbException {
        return cudadDao.listar_ciudades();
    }

    public void agrergar(Ciudad nuevaCiudad) throws Exception {
        cudadDao.insercionCiudad(nuevaCiudad);
    }

    public void actualizar(Ciudad nuevaCiudad) throws Exception {
        cudadDao.updateCiudad(nuevaCiudad);
    }

    public void eliminar(int id) throws Exception {
        cudadDao.deleteCiudad(id);
    }
}
