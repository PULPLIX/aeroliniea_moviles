/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DataAccess.ServicioRutas;
import Exceptions.DbException;
import Exceptions.GeneralException;
import java.util.Collection;
import logic.Ruta;

/**
 *
 * @author david
 */
public class ModelRuta {
    
    private final ServicioRutas rutaDao;
    public static ModelRuta ModelInstance = null;

    public ModelRuta() throws GeneralException {
        rutaDao = ServicioRutas.getSingletonInstance();
    }

    public static ModelRuta getInstance() throws GeneralException {
        if (ModelInstance == null) {
            ModelInstance = new ModelRuta();
        }
        return ModelInstance;
    }

    public Ruta getRuta(int id) throws GeneralException, DbException {
        return rutaDao.getRuta(id);
    }

    public Collection listarRutas() throws GeneralException, DbException {
        return rutaDao.listar_rutas();
    }

    public void agrergar(Ruta nuevoRuta) throws Exception {
        rutaDao.insercionRuta(nuevoRuta);
    }

    public void actualizar(Ruta nuevoRuta) throws Exception {
        rutaDao.updateRuta(nuevoRuta);
    }

    public void eliminar(int id) throws Exception {
        rutaDao.deleteRuta(id);
    }
}
