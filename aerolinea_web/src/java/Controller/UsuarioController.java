/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Models.ModelUsuario;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logic.Usuario;

/**
 *
 * @author david
 */
@WebServlet(name = "usuario", urlPatterns = {"/usuario/login", "/usuario/registrar", "/usuario/perfil", "/usuario/login/show"})
public class UsuarioController extends HttpServlet {

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
            case "/usuario/login/show":
                this.showLogin(request, response);
            case "/usuario/login":
                this.doLogin(request, response);
                break;
            case "/usuario/registrar":
                //this.doRegistrar(request, response);
                break;
            case "/usuario/perfil":
                //this.doPerfilMostrar(request, response);
                break;

        }
    }

    protected void showLogin(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/global/login.jsp").forward(request, response);
    }

    protected void doLogin(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        try {
            Gson gson = new Gson();

            String id = (String) request.getParameter("id");
            String contrasena = (String) request.getParameter("contrasena");

            HttpSession session = request.getSession(true);
            //Usuario real = com.progra.restaurante.data.Model.instance().getUsuario(username, clave);

            ModelUsuario mUsuario = ModelUsuario.getInstance();
            if (!mUsuario.validarUsuario(id, contrasena)) {
                throw new Exception("Usuario no encontrado");
            }
            Usuario nuevoUsuario = mUsuario.getUsuario(id);
            session.setAttribute("usuario", nuevoUsuario);

//            response.setContentType("application/json; charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.write(gson.toJson(nuevoUsuario));
//            response.setStatus(200);
            request.getRequestDispatcher("/views/admin/gestionAviones.jsp").forward(request, response);

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
