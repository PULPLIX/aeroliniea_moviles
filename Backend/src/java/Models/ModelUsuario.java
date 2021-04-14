/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DataAccess.ServicioUsuario;
import Exceptions.DbException;
import Exceptions.GeneralException;
import java.io.Serializable;
import java.util.Collection;
import logic.Usuario;

/**
 *
 * @author david
 */
public class ModelUsuario implements Serializable {
    private final ServicioUsuario usuarioDao;
    public static ModelUsuario ModelInstance = null;

    public ModelUsuario() {
        usuarioDao = ServicioUsuario.getSingletonInstance();
    }

    public static ModelUsuario getInstance() {
        if (ModelInstance == null) {
            ModelInstance = new ModelUsuario();
        }
        return ModelInstance;
    }


    public boolean validarUsuario(String id, String contrasena) throws GeneralException, DbException {
        return usuarioDao.validaUsuario(id, contrasena);
    }

    public Usuario getUsuario(String id) throws GeneralException, DbException {
        return usuarioDao.getUsuario(id);
    }

    public void agrergar(Usuario nuevoUsuario) throws Exception {
        usuarioDao.insercionUsuario(nuevoUsuario);
    }
    
    public Collection listarUsuarios() throws GeneralException, DbException {
        return usuarioDao.listar_usuario();
    }
    
    public void actualizar(Usuario nuevoUsuario) throws Exception {
        usuarioDao.updateUsuario(nuevoUsuario);
    }
    
}
