/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.DbException;
import Exceptions.GeneralException;
import Models.ModelUsuario;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import logic.Usuario;

/**
 * REST Web Service
 *
 * @author david
 */
@Path("usuario")
public class UsuarioController {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of UsuarioResource
     */
    public UsuarioController() {
    }

    @GET
    @Path("/get/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public String getUsuario(@PathParam("id") int id) throws GeneralException, DbException, Exceptions.DbException {
        Gson gson = new Gson();
        ModelUsuario mUsuario = ModelUsuario.getInstance();
        Usuario usuario = mUsuario.getUsuario(String.valueOf(id));
        return gson.toJson(usuario);
    }

    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public boolean insertar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(id, Usuario.class);
        ModelUsuario mUsuario = ModelUsuario.getInstance();
        mUsuario.agrergar(usuario);
        return true;
    }

    @PUT
    @Path("/actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String actualizar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        Usuario usuario = gson.fromJson(id, Usuario.class);
        ModelUsuario mUsuario = ModelUsuario.getInstance();
        mUsuario.actualizar(usuario);
        ArrayList<Usuario> usuarios = (ArrayList<Usuario>) mUsuario.listarUsuarios();
        //Salida de la aplicacion
        return gson.toJson(usuarios);
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Usuario login(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        Usuario usuarioRest = gson.fromJson(id, Usuario.class);
        ModelUsuario mUsuario = ModelUsuario.getInstance();
        usuarioRest = mUsuario.getUsuario(usuarioRest.getId());
        if(!mUsuario.validarUsuario(usuarioRest.getId(), usuarioRest.getContrasena())) {
            throw new Exception("Usuario no encontrado");
        }
        return usuarioRest;
    }

}
