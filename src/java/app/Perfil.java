/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author rogeliotorres
 */
public class Perfil extends HttpServlet {
    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        
        
        if(session!=null&&session.getAttribute("logged_in")!=null)
        {
            if(session.getAttribute("logged_in").equals(true))
            {
                Database db = new Database();
                Object arguments[]={
                    session.getAttribute("user_id")
                };
                
                ResultSet rs = db.ExecQuery("Select * from users where id = ?", arguments);
                User user = new User();
                try {
                    rs.next();
                    user.setAddress(rs.getString("Address"));
                    user.setEmail(rs.getString("Email"));
                    user.setId(rs.getInt("Id"));
                    user.setLastName(rs.getString("LastName"));
                    user.setName(rs.getString("Name"));
                    user.setPassword(rs.getString("Password"));
                    user.setPhone(rs.getString("Phone"));
                    user.setPostalCode(rs.getInt("PostalCode"));
                    user.setRol(rs.getInt("rol"));
                    user.setUsername(rs.getString("Username"));
                } catch (SQLException ex) {
                    Logger.getLogger(Perfil.class.getName()).log(Level.SEVERE, null, ex);
                }
            
                db.CloseConnection();
                
                request.setAttribute("user", user);
                
                ServletContext context = session.getServletContext();
                String virtualPath = "img/users/"+String.valueOf(user.getId())+".jpg";
                String realPath = context.getRealPath(virtualPath);
                File fichero = new File(realPath);
            
                if(fichero.exists()) request.setAttribute("userPhoto", String.valueOf(user.getId())+".jpg");
                else request.setAttribute("userPhoto", "no-profile.jpg");
                
                RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/perfil.jsp");
                rd.forward(request, response);
                
            }else response.sendRedirect("");
        }
        else response.sendRedirect("");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
     * Handles the HTTP
     * <code>POST</code> method.
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
