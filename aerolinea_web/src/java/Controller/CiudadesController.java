/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.DbException;
import Exceptions.GeneralException;
import Models.ModelCiudad;
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
import logic.Ciudad;

/**
 * REST Web Service
 *
 * @author david
 */
@Path("ciudades")
public class CiudadesController {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of CiudadesController
     */
    public CiudadesController() {
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public String listar() throws GeneralException, DbException {
        Gson gson = new Gson();
        ModelCiudad mCiudad = ModelCiudad.getInstance();
        ArrayList<Ciudad> ciudades = (ArrayList<Ciudad>) mCiudad.listarCiudades();
        //Salida de la aplicacion
        return gson.toJson(ciudades);
    }

    @GET
    @Path("/get/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public String getCiudad(@PathParam("id") int id) throws GeneralException, DbException {
        Gson gson = new Gson();
        ModelCiudad mCiudad = ModelCiudad.getInstance();
        Ciudad ciudades = mCiudad.getCiudad(id);
        return gson.toJson(ciudades);

    }

    @PUT
    @Path("/actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String actualizar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        Ciudad ciudad = gson.fromJson(id, Ciudad.class);
        ModelCiudad mCiudad = ModelCiudad.getInstance();
        mCiudad.actualizar(ciudad);
        ArrayList<Ciudad> ciudades = (ArrayList<Ciudad>) mCiudad.listarCiudades();
        //Salida de la aplicacion
        return gson.toJson(ciudades);
    }

    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        Ciudad ruta = gson.fromJson(id, Ciudad.class);
        ModelCiudad mCiudad = ModelCiudad.getInstance();
        mCiudad.agrergar(ruta);
        ArrayList<Ciudad> ciudades = (ArrayList<Ciudad>) mCiudad.listarCiudades();
        //Salida de la aplicacion
        return gson.toJson(ciudades);
    }

    @DELETE
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        ModelCiudad mCiudad = ModelCiudad.getInstance();
        mCiudad.eliminar(Integer.parseInt(id));
        ArrayList<Ciudad> ciudades = (ArrayList<Ciudad>) mCiudad.listarCiudades();
        //Salida de la aplicacion
        return gson.toJson(ciudades);
    }
}
