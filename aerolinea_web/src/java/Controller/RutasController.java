/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.DbException;
import Exceptions.GeneralException;
import Models.ModelRuta;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
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
import logic.Ruta;

/**
 * REST Web Service
 *
 * @author david
 */
@Path("rutas")
public class RutasController {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RutasController
     */
    public RutasController() {
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public String listar() throws GeneralException, DbException {
        Gson gson = new Gson();
        ModelRuta mRuta = ModelRuta.getInstance();
        ArrayList<Ruta> rutas = (ArrayList<Ruta>) mRuta.listarRutas();
        //Salida de la aplicacion
        return gson.toJson(rutas);
    }

    @GET
    @Path("/get/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public String getRuta(@PathParam("id") int id) throws GeneralException, DbException {
        Gson gson = new Gson();
        ModelRuta mRuta = ModelRuta.getInstance();
        Ruta rutas = mRuta.getRuta(id);
        return gson.toJson(rutas);
    }

    @PUT
    @Path("/actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String actualizar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        Ruta ruta = gson.fromJson(id, Ruta.class);
        ModelRuta mRuta = ModelRuta.getInstance();
        mRuta.actualizar(ruta);
        ArrayList<Ruta> rutas = (ArrayList<Ruta>) mRuta.listarRutas();
        //Salida de la aplicacion
        return gson.toJson(rutas);
    }

    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertar(String rutaStr) throws GeneralException, Exception {
        Gson gson = new Gson();
        //JsonObject jsonObject = gson.fromJson( ruta, JsonObject.class);
        Ruta ruta = gson.fromJson(rutaStr, Ruta.class);
        
        ModelRuta mRuta = ModelRuta.getInstance();
        mRuta.agrergar(ruta);
        ArrayList<Ruta> rutas = (ArrayList<Ruta>) mRuta.listarRutas();
        //Salida de la aplicacion
        return gson.toJson(rutas);
    }

    @DELETE
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        ModelRuta mRuta = ModelRuta.getInstance();
        mRuta.eliminar(Integer.parseInt(id));
        ArrayList<Ruta> rutas = (ArrayList<Ruta>) mRuta.listarRutas();
        //Salida de la aplicacion
        return gson.toJson(rutas);
    }
}
