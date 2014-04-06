/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.servlet.ServletRequestContext;

/**
 *
 * @author rogeliotorres
 */
public class cambiarImagenPerfil extends HttpServlet {

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
                File destino = new File(""+request.getSession().getServletContext().getRealPath("img/users"));
                ServletRequestContext src = new ServletRequestContext(request);
                if(ServletFileUpload.isMultipartContent(src))
                {
                    try {
                        DiskFileItemFactory factory = new DiskFileItemFactory((1024*1024),destino);
                        ServletFileUpload upload = new  ServletFileUpload(factory);

                        java.util.List lista = upload.parseRequest(src);
                        
                        java.util.Iterator it = lista.iterator();

                        while(it.hasNext()){
                            FileItem item=(FileItem)it.next();
                            if(item.isFormField())
                            {
                                
                            }
                            else
                            {
                                
                                item.write(new File(destino, request.getParameter("id").concat(".jpg")));
                            }
                        }

                    } catch (Exception ex) {
                        Logger.getLogger("cambiarImagenPerfil").log(Level.SEVERE, null, ex);
                    }
                    response.sendRedirect("perfil");
                }
                
            }
        }
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
