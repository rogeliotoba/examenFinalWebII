/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Department;
import models.Product;

/**
 *
 * @author rogeliotorres
 */
public class Carrito extends HttpServlet {

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
            Database db = new Database();
            
            Object args[] = {
                session.getAttribute("user_id")
            };
            
            ResultSet rsProducts = db.ExecQuery("Select * from ShoppingCart S join products P on S.ProductId=P.Id where UserId = ?", args);
            
            List<Product> products = new ArrayList();
            
            try {
                while(rsProducts.next())
                {
                    ServletContext context = session.getServletContext();
                    String virtualPath = "img/products/"+String.valueOf(rsProducts.getInt("Id"))+".jpg";
                    String realPath = context.getRealPath(virtualPath);
                    File fichero = new File(realPath);
            
                    String imageUrl = " ";
                    if(fichero.exists()) imageUrl = String.valueOf(rsProducts.getInt("Id"))+".jpg";
                    else imageUrl = "no-image.png";
                
                    products.add(new Product(rsProducts.getInt("Id"),
                                                          rsProducts.getInt("DepartmentId"),
                                                          "",
                                                          rsProducts.getString("Name"),
                                                          rsProducts.getString("Description"),
                                                          rsProducts.getFloat("Price"),
                                                          rsProducts.getInt("Quantity"),
                                                          rsProducts.getBoolean("Active"),
                                                          imageUrl,
                                                          rsProducts.getInt("AmountProduct")));
                }
            } catch (SQLException ex) {
                Logger.getLogger(Carrito.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.setAttribute("products", products);
            
            db.CloseConnection();
            
            RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/carrito.jsp");
            rd.forward(request, response);
        }
        else response.sendRedirect("/");
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
