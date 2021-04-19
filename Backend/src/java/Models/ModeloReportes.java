/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DataAccess.ServicioReportes;
import Exceptions.DbException;
import Exceptions.GeneralException;
import java.io.Serializable;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author david
 */
public class ModeloReportes  implements Serializable{

    private final ServicioReportes reportesDao;
    public static ModeloReportes ModelInstance = null;

    public ModeloReportes() throws GeneralException {
        reportesDao = ServicioReportes.getSingletonInstance();
    }

    public static ModeloReportes getInstance() throws GeneralException {
        if (ModelInstance == null) {
            ModelInstance = new ModeloReportes();
        }
        return ModelInstance;
    }

    public ArrayList<Pair<Integer, String>> facturadoXAnio() throws GeneralException, DbException {
        return reportesDao.facturadoXAnio();
    }

    public ArrayList<Pair<Integer, String>> ingresosUltimos12Meses() throws GeneralException, DbException {
        return reportesDao.ingresosUltimos12Meses();
    }

    public ArrayList<Pair<Integer, String>> top5Rutas() throws GeneralException, DbException {
        return reportesDao.top5Rutas();
    }
}
