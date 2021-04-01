/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.GeneralException;
import Models.ModelAvion;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import logic.Avion;

/**
 * REST Web Service
 *
 * @author david
 */
@Path("aviones")
public class AvionesController {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of AvionesController
     */
    public AvionesController() {
    }

    /**
     * Retrieves representation of an instance of Controller.AvionesController
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public String listar() throws GeneralException, DbException, Exceptions.DbException {
        Gson gson = new Gson();
        ModelAvion mAvion = ModelAvion.getInstance();
        ArrayList<Avion> aviones = (ArrayList<Avion>) mAvion.listarAviones();
        //Salida de la aplicacion
        return gson.toJson(aviones);
    }
    
    @GET
    @Path("/get/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public String getAvion(@PathParam("id") int id) throws GeneralException, DbException, Exceptions.DbException {
        Gson gson = new Gson();
        ModelAvion mAvion = ModelAvion.getInstance();
        Avion avion = mAvion.getAvion(id);
        return gson.toJson(avion);
    }
    
    @PUT
    @Path("/actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String actualizar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        Avion avion = gson.fromJson(id, Avion.class);
        ModelAvion mAvion = ModelAvion.getInstance();
        mAvion.actualizar(avion);
        ArrayList<Avion> aviones = (ArrayList<Avion>) mAvion.listarAviones();
        //Salida de la aplicacion
        return gson.toJson(aviones);
    }
    
    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        Avion avion = gson.fromJson(id, Avion.class);
        ModelAvion mAvion = ModelAvion.getInstance();
        mAvion.agrergar(avion);
        ArrayList<Avion> aviones = (ArrayList<Avion>) mAvion.listarAviones();
        //Salida de la aplicacion
        return gson.toJson(aviones);
    }

    
    @DELETE
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        ModelAvion mAvion = ModelAvion.getInstance();
        mAvion.eliminar(Integer.parseInt(id));
        ArrayList<Avion> aviones = (ArrayList<Avion>) mAvion.listarAviones();
        //Salida de la aplicacion
        return gson.toJson(aviones);
    }
}
