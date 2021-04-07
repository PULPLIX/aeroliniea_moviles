/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.DbException;
import Exceptions.GeneralException;
import Models.ModelRuta;
import Models.ModelTiquete;
import com.google.gson.Gson;
import java.util.ArrayList;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import logic.Ruta;
import logic.Tiquete;
import logic.Vuelo;

/**
 * REST Web Service
 *
 * @author david
 */
@Path("tiquetes")
public class TiquetesController {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TiquetesResource
     */
    public TiquetesController() {
    }
    
    
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public String listar() throws GeneralException, DbException {
        Gson gson = new Gson();
        ModelTiquete mTiquete = ModelTiquete.getInstance();
        ArrayList<Tiquete> tiquetes = (ArrayList<Tiquete>) mTiquete.listarTiquete();
        //Salida de la aplicacion
        return gson.toJson(tiquetes);
    }
    
    
    
    /**
     * Retrieves representation of an instance of Controller.TiquetesController
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of TiquetesController
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
