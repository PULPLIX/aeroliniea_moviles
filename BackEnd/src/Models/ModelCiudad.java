/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DataAccess.ServicioCiudad;
import Exceptions.DbException;
import Exceptions.GeneralException;
import java.util.Collection;
import logic.Ciudad;

/**
 *
 * @author david
 */
public class ModelCiudad {

    private final ServicioCiudad rutaDao;
    public static ModelCiudad ModelInstance = null;

    public ModelCiudad() throws GeneralException {
        rutaDao = ServicioCiudad.getSingletonInstance();
    }

    public static ModelCiudad getInstance() throws GeneralException {
        if (ModelInstance == null) {
            ModelInstance = new ModelCiudad();
        }
        return ModelInstance;
    }

    public Ciudad getCiudad(int id) throws GeneralException, DbException {
        return rutaDao.getCiudad(id);
    }

    public Collection listarCiudades() throws GeneralException, DbException {
        return rutaDao.listar_ciudades();
    }

    public void agrergar(Ciudad nuevaCiudad) throws Exception {
        rutaDao.insercionCiudad(nuevaCiudad);
    }

    public void actualizar(Ciudad nuevaCiudad) throws Exception {
        rutaDao.updateCiudad(nuevaCiudad);
    }

    public void eliminar(int id) throws Exception {
        rutaDao.deleteCiudad(id);
    }
}
