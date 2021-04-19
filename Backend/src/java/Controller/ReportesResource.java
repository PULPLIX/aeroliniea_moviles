/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.DbException;
import Exceptions.GeneralException;
import Models.ModelRuta;
import Models.ModeloReportes;
import com.google.gson.Gson;
import java.util.ArrayList;
import javafx.util.Pair;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import logic.Ruta;

/**
 * REST Web Service
 *
 * @author david
 */
@Path("reportes")
public class ReportesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ReportesResource
     */
    public ReportesResource() {
    }

    @GET
    @Path("/ultimosMeses")
    @Produces(MediaType.APPLICATION_JSON)
    public String ultimos12Meses() throws GeneralException, DbException {
        Gson gson = new Gson();

        ModeloReportes mReportes = ModeloReportes.getInstance();
        ArrayList<Pair<Integer, String>> ultimos12 = mReportes.ingresosUltimos12Meses();
        //Salida de la aplicacion
        return gson.toJson(ultimos12);
    }

    @GET
    @Path("/facturadoXAnio")
    @Produces(MediaType.APPLICATION_JSON)
    public String facturadoXAnio() throws GeneralException, DbException {
        Gson gson = new Gson();
        //Salida de la aplicacion
        ModeloReportes mReportes = ModeloReportes.getInstance();
        ArrayList<Pair<Integer, String>> facturado = mReportes.facturadoXAnio();
        return gson.toJson(facturado);
    }

    @GET
    @Path("/top5Rutas")
    @Produces(MediaType.APPLICATION_JSON)
    public String top5Rutas() throws GeneralException, DbException {
        Gson gson = new Gson();
        //Salida de la aplicacion
        ModeloReportes mReportes = ModeloReportes.getInstance();
        ArrayList<Pair<Integer, String>> top5 = mReportes.top5Rutas();
        return gson.toJson(top5);
    }
}
