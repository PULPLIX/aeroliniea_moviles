/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Exceptions.DbException;
import Exceptions.GeneralException;
import Models.ModelTiquete;
import Models.ModelVuelo;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import logic.Tiquete;
import logic.Usuario;
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
        return gson.toJson(tiquetes);
    }

    /**
     * Retrieves representation of an instance of Controller.TiquetesController
     *
     * @return an instance of java.lang.String
     */
    @POST
    @Path("/comprar")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String comprar(String data) throws GeneralException, DbException, Exception {
        Gson gson = new Gson();
        //Se convierte el array de datos a un objeto tipo array de Java
        ArrayList<String> dataArray = gson.fromJson(data, ArrayList.class);

        //Se obtiene el usuario que se encuentra en sesión
        Usuario usuario = gson.fromJson(dataArray.get(0), Usuario.class);
        //Se obtiene el Id de del vuelo 
        String vueloId = gson.fromJson(dataArray.get(1), String.class);

        //Se instancia un nuevo modelo del vuelo para poder buscarlo por id
        ModelVuelo mVuelo = ModelVuelo.getInstance();
        Vuelo vuelo = mVuelo.getVuelo(Integer.parseInt(vueloId)); //Se busca el vuelo que se desea comprar

        //Se parsean los asientos que hayan sido elejidos por el cliente
        ArrayList<ArrayList<String>> asientos = gson.fromJson(dataArray.get(2), ArrayList.class);
        
        //Se obtiene la forma de pago que haya elejido el cliente
        String formaPago = gson.fromJson(dataArray.get(3), String.class);
        
        //Se hacen los cálculos para obtener el precio final del tiquete
        double precioRuta = vuelo.getRutaId().getPrecio();
        double precioFinal = precioRuta - (precioRuta * (vuelo.getRutaId().getPorcentajeDescuento() * 0.01) );
        //Se crea un modeloTiquete para poder insertar iterativamente cada uno de los asientos seleccionados por el cliente
        ModelTiquete mTiquete = ModelTiquete.getInstance();
        //Se itera por cada uno de los asientos que haya elegido el cliente. 
        for (int i = 0; i < asientos.size(); i++) {
            int col = Integer.parseInt(asientos.get(i).get(0));
            int fila = Integer.parseInt(asientos.get(i).get(1));
            Tiquete nuevoTiquete = new Tiquete(0, usuario, vuelo, precioFinal, fila, col, formaPago);
            mTiquete.agrergar(nuevoTiquete);
        }
        return gson.toJson(asientos);
    }

    /**
     * PUT method for updating or creating an instance of TiquetesController
     *
     * @param content representation for the resource
     */
    
    @GET
    @Path("/eliminar/{id_tiquete}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String eliminar(@PathParam("id_tiquete") String id) throws GeneralException, Exception {
        Gson gson = new Gson();
        ModelTiquete mTiquete = ModelTiquete.getInstance();
        mTiquete.eliminar(Integer.parseInt(id));
        ArrayList<Tiquete> tiquetes = (ArrayList<Tiquete>) mTiquete.listarTiquete();
        
        //Salida de la aplicacion
        return gson.toJson(tiquetes);
    }
}
