/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DataAccess.ServicioVuelo;
import Exceptions.DbException;
import Exceptions.GeneralException;
import java.util.Collection;
import logic.Vuelo;

/**
 *
 * @author david
 */
public class ModelVuelo {

    private final ServicioVuelo vueloDao;
    public static ModelVuelo ModelInstance = null;

    public ModelVuelo() throws GeneralException {
        vueloDao = ServicioVuelo.getSingletonInstance();
    }

    public static ModelVuelo getInstance() throws GeneralException {
        if (ModelInstance == null) {
            ModelInstance = new ModelVuelo();
        }
        return ModelInstance;
    }

    public Vuelo getVuelo(int id) throws GeneralException, DbException {
        return vueloDao.getVuelo(id);
    }

    public Collection listarVuelos() throws GeneralException, DbException {
        return vueloDao.listar_vuelos();
    }

    public void agrergar(Vuelo nuevoVuelo) throws Exception {
        vueloDao.insercionVuelo(nuevoVuelo);
    }

    public void actualizar(Vuelo nuevoVuelo) throws Exception {
        vueloDao.updateVuelo(nuevoVuelo);
    }

    public void eliminar(int id) throws Exception {
        vueloDao.deleteVuelo(id);
    }
    
    public Collection filtrarVuelo(String Modalidad,String idOrigen, String idDestino, String fechaI, String fechaF) throws Exception {
        return vueloDao.filtrarVuelo(Modalidad,idOrigen,idDestino,fechaI,fechaF);
    }
}
