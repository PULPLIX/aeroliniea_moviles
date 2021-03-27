/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import DataAccess.ServicioUsuario;
import Exceptions.DbException;
import Exceptions.GeneralException;
import logic.Usuario;

/**
 *
 * @author david
 */
public class ModelUsuario {

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
    
    public boolean validarUsuario(String id, String contrasena) throws GeneralException, DbException {
        ServicioUsuario servUsuario =  ServicioUsuario.getSingletonInstance();
        return servUsuario.validaUsuario(id, contrasena);
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void agrergar() throws Exception{
    //aerolinea.logica.ModelUsuario.instanciar().agregar(usuario);
    }
    

}
