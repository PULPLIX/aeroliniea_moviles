/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.DbException;
import Exceptions.GeneralException;
import Models.ModelHorario;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import logic.Horario;

/**
 * REST Web Service
 *
 * @author david
 */
@Path("horarios")
public class HorariosController {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HorariosController
     */
    public HorariosController() {
    }

    /**
     * Retrieves representation of an instance of Controller.HorariosController
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Path("/listar")
    @Produces(MediaType.APPLICATION_JSON)
    public String listar() throws GeneralException, DbException {
        Gson gson = new Gson();
        ModelHorario mHorario = ModelHorario.getInstance();
        ArrayList<Horario> horarios = (ArrayList<Horario>) mHorario.listarHorarios();
        //Salida de la aplicacion
        return gson.toJson(horarios);
    }

    @GET
    @Path("/get/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.APPLICATION_JSON})
    public String getHorario(@PathParam("id") int id) throws GeneralException, DbException {
        Gson gson = new Gson();
        ModelHorario mHorario = ModelHorario.getInstance();
        Horario horario = mHorario.getHorario(id);
        return gson.toJson(horario);

    }

    @PUT
    @Path("/actualizar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String actualizar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        Horario horario = gson.fromJson(id, Horario.class);
        ModelHorario mHorario = ModelHorario.getInstance();
        mHorario.actualizar(horario);
        ArrayList<Horario> horarios = (ArrayList<Horario>) mHorario.listarHorarios();
        //Salida de la aplicacion
        return gson.toJson(horarios);
    }

    @POST
    @Path("/insertar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String insertar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        Horario horario = gson.fromJson(id, Horario.class);
        ModelHorario mHorario = ModelHorario.getInstance();
        mHorario.agrergar(horario);
        ArrayList<Horario> horarios = (ArrayList<Horario>) mHorario.listarHorarios();
        //Salida de la aplicacion
        return gson.toJson(horarios);
    }

    @DELETE
    @Path("/eliminar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminar(String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        ModelHorario mHorario = ModelHorario.getInstance();
        mHorario.eliminar(Integer.parseInt(id));
        ArrayList<Horario> horarios = (ArrayList<Horario>) mHorario.listarHorarios();
        //Salida de la aplicacion
        return gson.toJson(horarios);
    }

}
