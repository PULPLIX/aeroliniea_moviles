/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.ModelAvion;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Avion;
import logic.Usuario;

/**
 *
 * @author david
 */
@WebServlet(name = "aviones", urlPatterns = {"/aviones/show", "/aviones/insertar", "/aviones/perfil"})
public class AvionController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch (request.getServletPath()) {
            case "/aviones/show":
                this.showAviones(request, response);
                break;
            case "/aviones/insertar":
                this.doInsertar(request, response);
                break;
        }
    }

    protected void showAviones(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/gestionAviones.jsp").forward(request, response);
    }

    protected void doInsertar(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            BufferedReader reader = request.getReader();

            Avion avion = gson.fromJson(reader.readLine(), Avion.class);

            /*            String tipo = (String) request.getParameter("tipo");
            String capacidad = (String) request.getParameter("capacidad");
            String anno = (String) request.getParameter("anno");
            String marca = (String) request.getParameter("marca");
            String asientosFila = (String) request.getParameter("asientosFila");
            String asientosColumna = (String) request.getParameter("asientosColumna");
             */
            ModelAvion mAvion = ModelAvion.getInstance();
            mAvion.agrergar(avion);
            ArrayList<Avion> aviones = (ArrayList<Avion>) mAvion.listarAviones();
            //Salida de la aplicacion
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(aviones));
            response.setStatus(201); // ok with content

        } catch (Exception e) {
            response.setStatus(status(e));
        }
    }

    protected int status(Exception e) {
        if (e.getMessage().startsWith("404")) {
            return 404;
        }
        if (e.getMessage().startsWith("406")) {
            return 406;
        }
        return 400;
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
