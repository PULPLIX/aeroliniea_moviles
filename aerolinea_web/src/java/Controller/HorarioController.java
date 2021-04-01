package Controller;

import Exceptions.DbException;
import Exceptions.GeneralException;
import Models.ModelAvion;
import Models.ModelHorario;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Avion;
import logic.Horario;
import logic.Usuario;

/**
 *
 * @author david
 */
@WebServlet(name = "horarios", urlPatterns = {"/horarios/show", "/horarios/insertar",
    "/horarios/actualizar", "/horarios/eliminar", "/horarios/get", "/horarios/listar"})
public class HorarioController extends HttpServlet {

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
            case "/horarios/show":
                this.showHorarios(request, response);
                break;
            case "/horarios/get":
                this.getHorario(request, response);
                break;
            case "/horarios/insertar":
                this.doInsertar(request, response);
                break;
            case "/horarios/listar":
                this.doListarHorarios(request, response);
                break;
            case "/horarios/actualizar":
                this.doActualizarHorario(request, response);
                break;
            case "/horarios/eliminar":
                this.doEliminarHorario(request, response);
                break;
        }
    }

    protected void showHorarios(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/admin/gestionHorarios.jsp").forward(request, response);
    }

    protected void doActualizarHorario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            BufferedReader reader = request.getReader();

            Horario horario = gson.fromJson(reader.readLine(), Horario.class);

            ModelHorario mHorario = ModelHorario.getInstance();
            mHorario.actualizar(horario);
            ArrayList<Horario> horarios = (ArrayList<Horario>) mHorario.listarHorarios();
            //Salida de la aplicacion
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(horarios));
            response.setStatus(201); // ok with content
        } catch (Exception e) {
            response.setStatus(status(e));
        }
    }

    protected void doListarHorarios(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            Gson gson = new Gson();

            ModelHorario mHorario = ModelHorario.getInstance();
            ArrayList<Horario> horarios = (ArrayList<Horario>) mHorario.listarHorarios();
            //Salida de la aplicacion
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(horarios));
            response.setStatus(201); // ok with content
        } catch (GeneralException ex) {
            Logger.getLogger(HorarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DbException ex) {
            Logger.getLogger(HorarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void doInsertar(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            BufferedReader reader = request.getReader();

            Horario horario = gson.fromJson(reader.readLine(), Horario.class);

            ModelHorario mHorario = ModelHorario.getInstance();
            mHorario.agrergar(horario);
            ArrayList<Horario> horarios = (ArrayList<Horario>) mHorario.listarHorarios();
            //Salida de la aplicacion
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(horarios));
            response.setStatus(201); // ok with content

        } catch (Exception e) {
            response.setStatus(status(e));
        }
    }

    protected void getHorario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            BufferedReader reader = request.getReader();

            //String id = request.getParameter("id");
            String id = reader.readLine();
            ModelHorario mHorario = ModelHorario.getInstance();
            Horario horario = mHorario.getHorario(Integer.parseInt(id));

            //Salida de la aplicacion
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(horario));
            response.setStatus(201); // ok with content

        } catch (Exception e) {
            response.setStatus(status(e));
        }
    }

    protected void doEliminarHorario(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            Gson gson = new Gson();
            BufferedReader reader = request.getReader();

            //String id = request.getParameter("id");
            String id = reader.readLine();
            ModelHorario mHorario = ModelHorario.getInstance();
            mHorario.eliminar(Integer.parseInt(id));

            ArrayList<Horario> horarios = (ArrayList<Horario>) mHorario.listarHorarios();
            //Salida de la aplicacion
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.write(gson.toJson(horarios));
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
