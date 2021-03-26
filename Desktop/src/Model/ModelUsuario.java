/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DataAccess.ServicioUsuario;
import Exceptions.DbException;
import Exceptions.GeneralException;
import java.util.Observable;
import logic.Usuario;

/**
 *
 * @author david
 */
public class ModelUsuario extends Observable{

    Usuario usuario;
    public ModelUsuario() {
        this.usuario = new Usuario();
    }
    
    public ModelUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @Override
    public void addObserver(java.util.Observer o) {
        super.addObserver(o);
        setChanged();
        notifyObservers(null); // parametro no usado por ahora
    }
    
    public boolean validarUsuario(String id, String contrasena) throws GeneralException, DbException {
        ServicioUsuario servUsuario =  ServicioUsuario.getSingletonInstance();
        return servUsuario.validaUsario(id, contrasena);
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void agrergar() throws Exception{
    //aerolinea.logica.ModelUsuario.instanciar().agregar(usuario);
    }
    

}
