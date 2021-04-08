/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.DbException;
import Exceptions.GeneralException;
import Models.ModelVuelo;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import logic.Vuelo;

/**
 * REST Web Service
 *
 * @author david
 */
@Path("vuelos")
public class VuelosController {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of VuelosController
     */
    public VuelosController() {
    }

    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public String listar() throws GeneralException, DbException {
        Gson gson = new Gson();
        ModelVuelo mVuelo = ModelVuelo.getInstance();
        ArrayList<Vuelo> vuelos = (ArrayList<Vuelo>) mVuelo.listarVuelos();
        //Salida de la aplicacion
        return gson.toJson(vuelos);
    }

    @GET
    @Path("/buscar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public String filtrarVuelo(@QueryParam("Modalidad") String Modalidad, @QueryParam("idOrigen") String idOrigen, @QueryParam("idDestino") String idDestino, @QueryParam("fechaI") String fechaI, @QueryParam("fechaF") String fechaF) throws GeneralException, DbException, Exception {
        Gson gson = new Gson();
        ModelVuelo mVuelo = ModelVuelo.getInstance();
        ArrayList<Vuelo> vuelos = (ArrayList<Vuelo>) mVuelo.filtrarVuelo(Modalidad, idOrigen, idDestino, fechaI, fechaF);
        return gson.toJson(vuelos);
    }

    @GET
    @Path("/get/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public String getVuelo(@PathParam("id") int id) throws GeneralException, DbException {
        Gson gson = new Gson();
        ModelVuelo mVuelo = ModelVuelo.getInstance();
        Vuelo vuelos = mVuelo.getVuelo(id);
        return gson.toJson(vuelos);
    }

    @PUT
    @Path("/actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String actualizar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        Vuelo vuelo = gson.fromJson(id, Vuelo.class);
        ModelVuelo mVuelo = ModelVuelo.getInstance();
        mVuelo.actualizar(vuelo);
        ArrayList<Vuelo> vuelos = (ArrayList<Vuelo>) mVuelo.listarVuelos();
        //Salida de la aplicacion
        return gson.toJson(vuelos);
    }

    @GET
    @Path("/asientosOcupados/{id_vuelo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public String getAsieentosOcupados(@PathParam("id_vuelo") int id) throws GeneralException, DbException, Exception {
        Gson gson = new Gson();
        ModelVuelo mVuelo = ModelVuelo.getInstance();
        HashMap<Integer, ArrayList<Integer>> asientosOcu = mVuelo.getAsientosOcupados(id);
        String jsonHash = gson.toJson(asientosOcu);
        return gson.toJson(jsonHash);
    }

    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertar(String vueloStr) throws GeneralException, Exception {
        Gson gson = new Gson();
        Vuelo vuelo = gson.fromJson(vueloStr, Vuelo.class);

        ModelVuelo mVuelo = ModelVuelo.getInstance();
        mVuelo.agrergar(vuelo);
        ArrayList<Vuelo> vuelos = (ArrayList<Vuelo>) mVuelo.listarVuelos();
        //Salida de la aplicacion
        return gson.toJson(vuelos);
    }

    @DELETE
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        ModelVuelo mVuelo = ModelVuelo.getInstance();
        mVuelo.eliminar(Integer.parseInt(id));
        ArrayList<Vuelo> vuelos = (ArrayList<Vuelo>) mVuelo.listarVuelos();
        //Salida de la aplicacion
        return gson.toJson(vuelos);
    }
}
