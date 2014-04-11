/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author rogeliotorres
 */
public class addToCart extends HttpServlet {

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
        
        if(session!=null && session.getAttribute("logged_in")!=null)
        {
            String userIdString = request.getParameter("userId");
            String productIdString = request.getParameter("productId");
            String department = request.getParameter("department");
            if(department==null) department="1";
            
            if(userIdString!=null && productIdString!=null)
            {
                int userId = Integer.parseInt(userIdString);
                int productId = Integer.parseInt(productIdString);
                    
                Database db = new Database();
                    
                //verify if the item already exists    
                ResultSet rsVerify = db.ExecQuery("Select * from ShoppingCart where ProductId=? and UserId=?", new Object[]{productId,userId});
                try {
                    if(rsVerify.next())
                    {
                        db.ExecUpdate("Update ShoppingCart set AmountProduct=AmountProduct+1 where UserId=? and ProductId=?", new Object[]{userId,productId});
                    }
                    else
                    {
                        System.out.println("Nuevo registro creado");
                        db.ExecUpdate("Insert into ShoppingCart values(?,?,?)", new Object[]{userId,productId,1});
                    }
                    
                    db.CloseConnection();
                } catch (SQLException ex) {
                    Logger.getLogger(addToCart.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            response.sendRedirect("/examenFinalWebII/main?department="+department);
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
