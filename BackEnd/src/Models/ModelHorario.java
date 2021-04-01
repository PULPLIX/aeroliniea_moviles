/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DataAccess.ServicioHorario;
import Exceptions.DbException;
import Exceptions.GeneralException;
import java.io.Serializable;
import java.util.Collection;
import logic.Horario;

/**
 *
 * @author david
 */
public class ModelHorario {

    private final ServicioHorario horarioDao;
    public static ModelHorario ModelInstance = null;

    public ModelHorario() throws GeneralException {
        horarioDao = ServicioHorario.getSingletonInstance();
    }

    public static ModelHorario getInstance() throws GeneralException {
        if (ModelInstance == null) {
            ModelInstance = new ModelHorario();
        }
        return ModelInstance;
    }

    public Horario getHorario(int id) throws GeneralException, DbException {
        return horarioDao.getHorario(id);
    }

    public Collection listarHorarios() throws GeneralException, DbException {
        return horarioDao.listar_horario();
    }

    public void agrergar(Horario nuevoHorario) throws Exception {
        horarioDao.insercionHorario(nuevoHorario);
    }

    public void actualizar(Horario nuevoHorario) throws Exception {
        horarioDao.updateHorario(nuevoHorario);
    }

    public void eliminar(int id) throws Exception {
        horarioDao.deleteHorario(id);
    }
}
